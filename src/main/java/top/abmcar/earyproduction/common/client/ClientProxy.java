package top.abmcar.earyproduction.common.client;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import top.abmcar.earyproduction.EasyProduction;
import top.abmcar.earyproduction.common.model.ItemModelManager;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = EasyProduction.MODID)
public class ClientProxy
{
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        ItemModelManager.register();
    }
}