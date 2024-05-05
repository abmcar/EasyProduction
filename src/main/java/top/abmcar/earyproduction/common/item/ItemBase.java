package top.abmcar.earyproduction.common.item;

import net.minecraft.item.Item;
import top.abmcar.earyproduction.EasyProduction;
import top.abmcar.earyproduction.common.tab.EasyProductionTab;
import top.abmcar.earyproduction.utils.StringUtil;

public class ItemBase extends Item {
    public ItemBase(String registryName) {
        String unlocalizedName = StringUtil.registryNameToUnlocalizedName(registryName);
        this.setCreativeTab(EasyProductionTab.INSTANCE);
        this.setUnlocalizedName(String.format("%s.%s", EasyProduction.MODID, unlocalizedName));
        this.setRegistryName(EasyProduction.MODID, registryName);
    }
}
