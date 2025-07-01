package iamfmgod.performancemonitoringenhanc.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ServerTickHandler {
    private static final int TICK_INTERVAL = 10; // now half‐second updates
    private int tickCounter = 0;
    private long startTime = -1L;

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        if (startTime < 0) {
            startTime = System.nanoTime();
            tickCounter = 0;
            return;
        }

        tickCounter++;
        if (tickCounter < TICK_INTERVAL) return;

        long now = System.nanoTime();
        double elapsedSec = (now - startTime) / 1_000_000_000.0;
        float avgTps = (float)(tickCounter / elapsedSec);

        for (EntityPlayerMP player :
                FMLCommonHandler.instance()
                        .getMinecraftServerInstance()
                        .getPlayerList()
                        .getPlayers()) {
            int ping = player.ping;
            NetworkHandler.INSTANCE.sendTo(
                    new PacketTpsPing(avgTps, ping),
                    player
            );
        }

        // reset
        startTime   = now;
        tickCounter = 0;
    }
}