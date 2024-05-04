package top.abmcar.earyproduction.common.item;


import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import top.abmcar.earyproduction.common.tab.EasyProductionTab;


//@Mod.EventBusSubscriber(modid = "easyproduction")
public class TempItem extends ItemBase {
    public static TempItem tempItem = new TempItem();

    public TempItem() {
        super("temp");
        this.setMaxStackSize(1);
    }

}
