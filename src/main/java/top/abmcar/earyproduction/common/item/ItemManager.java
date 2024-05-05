package top.abmcar.earyproduction.common.item;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import top.abmcar.earyproduction.EasyProduction;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = EasyProduction.MODID)
public class ItemManager {
    private static final ArrayList<Item> items = new ArrayList<>();

    public static TimeInACard timeInACard = new TimeInACard();

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        addItems();
        for (Item item : items) {
            event.getRegistry().register(item);
        }
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    private static void addItems() {
        items.add(timeInACard);
    }

}
