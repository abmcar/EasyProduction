package top.abmcar.earyproduction.common.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import top.abmcar.earyproduction.utils.NBTUtil;

public class EntityTimeAccelerator extends Entity implements IEntityAdditionalSpawnData {

	int remainingTime;

	private static final DataParameter<Integer> TIME_RATE = EntityDataManager.createKey(EntityTimeAccelerator.class, DataSerializers.VARINT);

	BlockPos target;

	public EntityTimeAccelerator(World worldIn)
	{
		super(worldIn);

		this.setSize(0.1F, 0.1F);

		this.noClip = true;

		this.dataManager.register(TIME_RATE, 1);
	}

	public EntityTimeAccelerator(World worldIn, BlockPos target, double posX, double posY, double posZ)
	{
		this(worldIn);

		this.target = target;

		this.setPosition(posX, posY, posZ);
	}

	static Class cofhClass;

	static
	{
		try
		{
			cofhClass = Class.forName("cofh.core.block.TileCore");
		}
		catch (Exception ignored)
		{

		}
	}

	public int getRemainingTime()
	{
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime)
	{
		this.remainingTime = remainingTime;
	}

    public int getTimeRate()
	{
		return this.dataManager.get(TIME_RATE);
	}

	public void setTimeRate(int timeRate)
	{
		this.dataManager.set(TIME_RATE, timeRate);
	}

    @Override
	public void onEntityUpdate() {
        super.onEntityUpdate();
		TileEntity targetTE = this.world.getTileEntity(target);

		boolean isCofhBlock = false;

		if (cofhClass != null && targetTE instanceof ITickable)
		{
			isCofhBlock = cofhClass.isInstance(targetTE);
		}

		for (int i = 0; i < getTimeRate(); i++)
		{
			targetTE = this.world.getTileEntity(target);

			if (targetTE instanceof ITickable && (!isCofhBlock || !this.world.isRemote))
			{
				((ITickable) targetTE).update();
			}
            // 4096 // 3 = 1365
            // simulate vanilla random tick logic
			if (this.world.rand.nextInt(1365) == 0)
			{
				IBlockState targetBlock = world.getBlockState(target);

				if (targetBlock.getBlock().getTickRandomly())
				{
					targetBlock.getBlock().randomTick(world, target, targetBlock, world.rand);
				}
			}
		}

		this.remainingTime -= 1;

		if (this.remainingTime == 0 && !this.world.isRemote)
		{
			this.setDead();
		}
    }

    @Override
    protected void entityInit() {

    }

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		this.target = NBTUtil.readBlockPosFromNBT(compound, "target");
		this.remainingTime = compound.getInteger("remainingTime");
		setTimeRate(compound.getInteger("timeRate"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		NBTUtil.writeBlockPosToNBT(compound, "target", target);
		compound.setInteger("remainingTime", remainingTime);
		compound.setInteger("timeRate", getTimeRate());
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeInt(target.getX());
		buffer.writeInt(target.getY());
		buffer.writeInt(target.getZ());
	}

	@Override
	public void readSpawnData(ByteBuf ad) {
		this.target = new BlockPos(ad.readInt(), ad.readInt(), ad.readInt());
	}

}
