package top.abmcar.earyproduction.common.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import top.abmcar.earyproduction.EasyProduction;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = EasyProduction.MODID)
public class BlockManager {
    private static final ArrayList<Block> blocks = new ArrayList<Block>();

    public static TempBlock tempBlock = new TempBlock();

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        // 和物品一样，每一个方块都有唯一一个注册名，不能使用大写字母。
        addBlocks();
        for (Block block : blocks) {
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        // 注意这个 ItemBlock 使用了和它对应的方块一样的注册名。
        // 对于所有有物品形态的方块，其物品的注册名和它自己的注册名需要保持一致。
        addBlocks();
        for (Block block : blocks) {
            Item item = new ItemBlock(block).setRegistryName(block.getRegistryName());
            event.getRegistry().register(item);
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }

    public static ArrayList<Block> getBlocks() {
        return blocks;
    }

    private static void addBlocks() {
        if (!blocks.isEmpty()) {
            return;
        }
        blocks.add(tempBlock);
    }
}
