package iamfmgod.performancemonitoringenhanc.core;

import iamfmgod.performancemonitoringenhanc.network.NetworkHandler;
import iamfmgod.performancemonitoringenhanc.network.ServerTickHandler;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    /** Register packets/messages **/
    public void registerNetwork() {
        NetworkHandler.registerMessages();
    }

    /** Register server‐side only handlers **/
    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new ServerTickHandler());
    }

    /** No-op on server for keybindings/HUD **/
    public void initClient() { }
}