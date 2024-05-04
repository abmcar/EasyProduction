package top.abmcar.earyproduction.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import top.abmcar.earyproduction.EasyProduction;
import top.abmcar.earyproduction.common.tab.EasyProductionTab;

@Mod.EventBusSubscriber(modid = "easyproduction")
public class BlockInitializer {
    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        // 和物品一样，每一个方块都有唯一一个注册名，不能使用大写字母。
    event.getRegistry().register(
        TempBlock.INSTANCE
    );
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        // 注意这个 ItemBlock 使用了和它对应的方块一样的注册名。
        // 对于所有有物品形态的方块，其物品的注册名和它自己的注册名需要保持一致。
        event.getRegistry().register(
          new ItemBlock(TempBlock.INSTANCE).setRegistryName(EasyProduction.MODID, "temp_block")
        );
    }
}
