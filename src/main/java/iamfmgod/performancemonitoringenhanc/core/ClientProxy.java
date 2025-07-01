package iamfmgod.performancemonitoringenhanc.core;

import iamfmgod.performancemonitoringenhanc.hud.HudOverlay;
import iamfmgod.performancemonitoringenhanc.input.KeyHandler;
import iamfmgod.performancemonitoringenhanc.input.MouseDragHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

// This class is client-only. Do not reference from common or server code.
public class ClientProxy extends CommonProxy {
    @Override
    public void registerHandlers() {
        // still register server‐side tick handler
        super.registerHandlers();

        // client‐only HUD repaint happens in HudOverlay constructor
        MinecraftForge.EVENT_BUS.register(new HudOverlay());
        MinecraftForge.EVENT_BUS.register(new MouseDragHandler());
    }

    @Override
    public void initClient() {
        // register keybindings on client
        KeyHandler.init();
    }
}