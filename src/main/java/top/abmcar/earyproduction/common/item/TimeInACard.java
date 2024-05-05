package top.abmcar.earyproduction.common.item;


import ibxm.Player;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import top.abmcar.earyproduction.common.entity.EntityTimeAccelerator;

import java.util.List;
import java.util.Optional;


public class TimeInACard extends ItemBase {

    public TimeInACard() {
        super("time_in_a_card");
        this.setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        NBTTagCompound timeData = stack.getOrCreateSubCompound("timeData");
        int storedTime = timeData.getInteger("storedTime");
        int totalSeconds = storedTime / 20;
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        tooltip.add(I18n.format("tooltip.easyproduction.time_in_a_card", hours, minutes, seconds));
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return !ItemStack.areItemsEqual(oldStack, newStack);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        int secondWorth = 20;
        if (worldIn.isRemote) {
            return;
        }
        if (worldIn.getTotalWorldTime() % secondWorth != 0) {
            return;
        }
        NBTTagCompound timeData = stack.getOrCreateSubCompound("timeData");
        timeData.setInteger("storedTime", timeData.getInteger("storedTime") + 20);
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
        }

    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (world.isRemote) {
            return EnumActionResult.PASS;
        }
        ItemStack me = player.getHeldItem(hand);

        Optional<EntityTimeAccelerator> o = world.getEntitiesWithinAABB(EntityTimeAccelerator.class, new AxisAlignedBB(pos).shrink(0.2)).stream().findFirst();

        if (player.isSneaking()) {
            displayStatus(player, o);
            return EnumActionResult.SUCCESS;
        }

        if (o.isPresent()) {
            EntityTimeAccelerator eta = o.get();
            int currentRate = eta.getTimeRate();
            int usedUpTime = 20 * 30 - eta.getRemainingTime();

            if (currentRate < 32) {
                int nextRate = currentRate * 2;
                int timeRequired = nextRate / 2 * 20 * 30;
                NBTTagCompound timeData = me.getSubCompound("timeData");
                int timeAvailable = timeData.getInteger("storedTime");

                if (timeAvailable >= timeRequired || player.capabilities.isCreativeMode) {
                    int timeAdded = (nextRate * usedUpTime - currentRate * usedUpTime) / nextRate;

                    if (!player.capabilities.isCreativeMode)
                        timeData.setInteger("storedTime", timeAvailable - timeRequired);

                    eta.setTimeRate(nextRate);
                    eta.setRemainingTime(eta.getRemainingTime() + timeAdded);

                    switch (nextRate) {
                        case 2:
                            world.playSound(null, pos, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.BLOCKS, 0.5F, 0.793701F);
                            break;
                        case 4:
                            world.playSound(null, pos, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.BLOCKS, 0.5F, 0.890899F);
                            break;
                        case 8:
                            world.playSound(null, pos, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.BLOCKS, 0.5F, 1.059463F);
                            break;
                        case 16:
                            world.playSound(null, pos, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.BLOCKS, 0.5F, 0.943874F);
                            break;
                        case 32:
                            world.playSound(null, pos, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.BLOCKS, 0.5F, 0.890899F);
                            break;
                    }
                }
            }
        } else {
            NBTTagCompound timeData = me.getSubCompound("timeData");
            int timeAvailable = timeData.getInteger("storedTime");

            if (timeAvailable >= 20 * 30 || player.capabilities.isCreativeMode) {
                if (!player.capabilities.isCreativeMode)
                    timeData.setInteger("storedTime", timeAvailable - 20 * 30);

                EntityTimeAccelerator n = new EntityTimeAccelerator(world, pos, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

                n.setTimeRate(1);
                n.setRemainingTime(20 * 30);

                world.playSound(null, pos, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.BLOCKS, 0.5F, 0.749154F);
                world.spawnEntity(n);
            }
        }

        o = world.getEntitiesWithinAABB(EntityTimeAccelerator.class, new AxisAlignedBB(pos).shrink(0.2)).stream().findFirst();
        displayStatus(player, o);

        return EnumActionResult.SUCCESS;
    }

    private void displayStatus(EntityPlayer player, Optional<EntityTimeAccelerator> o) {
        String message = null;
        if (o.isPresent()) {
            EntityTimeAccelerator eta = o.get();
            message = I18n.format("message.easyproduction.time_in_a_card.status", eta.getTimeRate(), eta.getRemainingTime() / 20);
        } else {
            message = I18n.format("message.easyproduction.time_in_a_card.empty");
        }
        player.sendStatusMessage(new TextComponentString(message), true);
    }
}
