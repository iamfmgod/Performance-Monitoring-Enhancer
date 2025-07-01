package iamfmgod.performancemonitoringenhanc.hud;

import iamfmgod.performancemonitoringenhanc.config.HudConfig;
import iamfmgod.performancemonitoringenhanc.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class HudOverlay {
    private final Minecraft mc = Minecraft.getMinecraft();

    public HudOverlay() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() != ElementType.TEXT) return;
        if (!HudManager.isVisible()) return;

        int x = HudManager.getX();
        int y = HudManager.getY();

        // record FPS for percent-low calculation
        int fps = Minecraft.getDebugFPS();
        HudManager.addFpsSample(fps);

        String text = buildOverlayText(fps);
        RenderUtils.drawOverlayBox(x, y, text);
    }

    private static String buildOverlayText(int currentFps) {
        List<String> parts = new ArrayList<>();

        if (HudConfig.SHOW_FPS) {
            parts.add(String.format("FPS: %d", currentFps));
        }
        if (HudConfig.SHOW_ONE_PERCENT_LOW) {
            parts.add(String.format("1%% Low: %d", HudManager.getOnePercentLowFps()));
        }
        if (HudConfig.SHOW_ZERO_ZERO_ONE_LOW) {
            parts.add(String.format("0.01%% Low: %d", HudManager.getPointZeroZeroOnePercentLowFps()));
        }
        if (HudConfig.SHOW_TPS) {
            parts.add(String.format("TPS: %.1f", HudManager.getTps()));
        }
        if (HudConfig.SHOW_PING) {
            parts.add(String.format("Ping: %d ms", HudManager.getPing()));
        }

        return String.join(" | ", parts);
    }
}