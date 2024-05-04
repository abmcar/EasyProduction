package top.abmcar.earyproduction.common.item;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = "easyproduction")
public final class ItemInitializer {

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        for (Item item : ItemManager.getInstance().getItems()) {
            event.getRegistry().register(item);
        }
    }
}