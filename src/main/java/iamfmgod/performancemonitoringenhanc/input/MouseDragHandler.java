package iamfmgod.performancemonitoringenhanc.input;

import iamfmgod.performancemonitoringenhanc.config.HudConfig;
import iamfmgod.performancemonitoringenhanc.hud.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

@SideOnly(Side.CLIENT)
public class MouseDragHandler {
    private final Minecraft mc = Minecraft.getMinecraft();
    private boolean dragging = false;
    private int offsetX, offsetY;

    public MouseDragHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onMouse(MouseEvent event) {
        if (!HudManager.isMouseEnabled()) {
            return;
        }

        ScaledResolution res = new ScaledResolution(mc);
        int sw = mc.displayWidth;
        int sh = mc.displayHeight;
        int mouseX = event.getX() * res.getScaledWidth() / sw;
        int mouseY = res.getScaledHeight() - event.getY() * res.getScaledHeight() / sh;

        if (Mouse.isButtonDown(0)) {
            if (!dragging && isOverBox(mouseX, mouseY)) {
                dragging = true;
                offsetX = mouseX - HudManager.getX();
                offsetY = mouseY - HudManager.getY();
            } else if (dragging) {
                HudManager.setPosition(mouseX - offsetX, mouseY - offsetY);
            }
        } else {
            if (dragging) {
                HudConfig.save();
                dragging = false;
            }
        }
    }

    private boolean isOverBox(int mx, int my) {
        int padding = 4;
        // sample text long enough to approximate widest case
        String sample = "FPS: 000 | 1% Low: 000 | 0.01% Low: 000 | TPS: 20.0 | Ping: 999 ms";
        int w = mc.fontRenderer.getStringWidth(sample) + padding * 2;
        int h = mc.fontRenderer.FONT_HEIGHT + padding * 2;
        int x = HudManager.getX();
        int y = HudManager.getY();
        return mx >= x && mx <= x + w && my >= y && my <= y + h;
    }
}