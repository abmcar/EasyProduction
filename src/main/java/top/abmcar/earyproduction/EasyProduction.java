package top.abmcar.earyproduction;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = EasyProduction.MODID, name = EasyProduction.NAME, version = EasyProduction.VERSION)
public class EasyProduction
{
    @Mod.Instance("easyproduction")
    public static EasyProduction instance;

    public static final String MODID = "easyproduction";
    public static final String NAME = "Easy Production";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }
}
