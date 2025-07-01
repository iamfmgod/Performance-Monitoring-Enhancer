package iamfmgod.performancemonitoringenhanc.gui;

import iamfmgod.performancemonitoringenhanc.config.HudConfig;
import iamfmgod.performancemonitoringenhanc.hud.HudManager;
import iamfmgod.performancemonitoringenhanc.input.KeyHandler;
import iamfmgod.performancemonitoringenhanc.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public class HudDragScreen extends GuiScreen {
    private boolean dragging = false;
    private int offsetX = 0, offsetY = 0;

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        String text = buildOverlayText();
        RenderUtils.drawOverlayBox(HudManager.getX(), HudManager.getY(), text);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0 && isOverBox(mouseX, mouseY)) {
            dragging = true;
            offsetX  = mouseX - HudManager.getX();
            offsetY  = mouseY - HudManager.getY();
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int button, long time) {
        if (dragging) {
            HudManager.setPosition(mouseX - offsetX, mouseY - offsetY);
        }
        super.mouseClickMove(mouseX, mouseY, button, time);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        if (dragging) {
            HudConfig.save();
            dragging = false;
        }
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        // close on the same G key
        if (keyCode == KeyHandler.toggleMouseKey.getKeyCode()) {
            HudManager.toggleMouseEnabled();
            Mouse.setGrabbed(true);
            Minecraft.getMinecraft().displayGuiScreen(null);
        } else {
            super.keyTyped(typedChar, keyCode);
        }
    }

    private boolean isOverBox(int mx, int my) {
        FontRenderer fr = fontRenderer;
        String sample = buildOverlayText();
        int pad = 4;
        int w = fr.getStringWidth(sample) + pad*2;
        int h = fr.FONT_HEIGHT + pad*2;
        return mx >= HudManager.getX() && mx <= HudManager.getX()+w
                && my >= HudManager.getY() && my <= HudManager.getY()+h;
    }

    private static String buildOverlayText() {
        int fps    = Minecraft.getDebugFPS();
        int one    = HudManager.getOnePercentLowFps();
        int dot001 = HudManager.getPointZeroZeroOnePercentLowFps();
        float tps  = HudManager.getTps();
        int ping   = HudManager.getPing();
        return String.format(
                "FPS: %d | 1%% Low: %d | 0.01%% Low: %d | TPS: %.1f | Ping: %d ms",
                fps, one, dot001, tps, ping
        );
    }
}