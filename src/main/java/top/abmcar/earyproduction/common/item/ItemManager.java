package top.abmcar.earyproduction.common.item;

import net.minecraft.item.Item;

import java.util.ArrayList;

public class ItemManager {
    private static final ArrayList<Item> items = new ArrayList<>();
    private final static ItemManager INSTANCE = new ItemManager();

    public static ItemManager getInstance() {
        return INSTANCE;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    private ItemManager() {
        items.add(TempItem.tempItem);
    }

}
