package top.abmcar.earyproduction.common.model;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import top.abmcar.earyproduction.common.item.ItemManager;
import top.abmcar.earyproduction.common.item.TempItem;

public class ItemModelManager {
    public static void register() {
        for (Item item : ItemManager.getInstance().getItems()) {
            registerItem(item);
        }
    }

    private static void registerItem(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
