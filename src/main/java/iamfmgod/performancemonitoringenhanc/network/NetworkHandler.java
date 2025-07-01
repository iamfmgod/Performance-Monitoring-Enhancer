package iamfmgod.performancemonitoringenhanc.network;

import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class NetworkHandler {
    public static final SimpleNetworkWrapper INSTANCE =
            NetworkRegistry.INSTANCE.newSimpleChannel("PerfMonHUD");

    public static void registerMessages() {
        INSTANCE.registerMessage(PacketTpsPing.Handler.class, PacketTpsPing.class, 0, net.minecraftforge.fml.relauncher.Side.CLIENT);
    }
}