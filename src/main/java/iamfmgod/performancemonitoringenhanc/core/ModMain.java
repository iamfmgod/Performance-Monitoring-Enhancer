package iamfmgod.performancemonitoringenhanc.core;

import iamfmgod.performancemonitoringenhanc.config.HudConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid   = ModMain.MODID,
        name    = ModMain.NAME,
        version = ModMain.VERSION
)
public class ModMain {
    public static final String MODID   = "performancemonitoringenhanc";
    public static final String NAME    = "Performance Monitoring Enhancer";
    public static final String VERSION = "1.0";

    @SidedProxy(
            modId   = MODID,
            clientSide = "iamfmgod.performancemonitoringenhanc.core.ClientProxy",
            serverSide = "iamfmgod.performancemonitoringenhanc.core.CommonProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // load config on both sides
        HudConfig.init(event.getModConfigurationDirectory());

        // register network messages on both sides
        proxy.registerNetwork();

        // register side‐specific handlers
        proxy.registerHandlers();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // side‐specific init (keybindings, etc.)
        proxy.initClient();
    }
}