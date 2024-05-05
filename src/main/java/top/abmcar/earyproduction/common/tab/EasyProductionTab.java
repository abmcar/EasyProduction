package top.abmcar.earyproduction.common.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import top.abmcar.earyproduction.common.item.ItemManager;

public class EasyProductionTab  extends CreativeTabs{
    public static EasyProductionTab INSTANCE = new EasyProductionTab("easyproduction");
    private EasyProductionTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemManager.timeInACard);
    }
}
