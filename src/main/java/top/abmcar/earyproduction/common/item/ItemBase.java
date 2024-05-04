package top.abmcar.earyproduction.common.item;

import net.minecraft.item.Item;
import top.abmcar.earyproduction.EasyProduction;
import top.abmcar.earyproduction.common.tab.EasyProductionTab;

public class ItemBase extends Item {
    public ItemBase(String unlocalizedName) {
        this.setCreativeTab(EasyProductionTab.INSTANCE);
        this.setUnlocalizedName(String.format("%s.%s", EasyProduction.MODID, unlocalizedName));

        this.setRegistryName(EasyProduction.MODID, unlocalizedName);
    }
}
