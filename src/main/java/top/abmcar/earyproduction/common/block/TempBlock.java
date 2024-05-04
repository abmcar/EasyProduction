package top.abmcar.earyproduction.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.property.Properties;
import top.abmcar.earyproduction.common.tab.EasyProductionTab;

public class TempBlock extends Block {
    public static TempBlock INSTANCE = new TempBlock();
    public TempBlock() {
        super(Material.ROCK);
        this.setCreativeTab(EasyProductionTab.INSTANCE);
        this.setRegistryName("easyproduction","tempBlock");
        this.setUnlocalizedName("easyproduction.temp_block");
    }
}
