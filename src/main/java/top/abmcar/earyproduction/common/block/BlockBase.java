package top.abmcar.earyproduction.common.block;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import top.abmcar.earyproduction.EasyProduction;
import top.abmcar.earyproduction.common.tab.EasyProductionTab;
import top.abmcar.earyproduction.utils.StringUtil;

public class BlockBase extends Block {
    public BlockBase(String registryName) {
        super(Material.ROCK);
        String unlocalizedName = StringUtil.registryNameToUnlocalizedName(registryName);
        this.setUnlocalizedName(String.format("%s.%s", EasyProduction.MODID, unlocalizedName));
        this.setCreativeTab(EasyProductionTab.INSTANCE);
        this.setRegistryName(EasyProduction.MODID, registryName);
    }
}
