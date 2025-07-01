package iamfmgod.performancemonitoringenhanc.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class RenderUtils {
    /**
     * Draws a semi‐transparent background and border sized to the text,
     * then renders the text with shadow.
     */
    public static void drawOverlayBox(int x, int y, String text) {
        FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
        int padding = 4;

        // calculate box dimensions
        int width  = fr.getStringWidth(text) + padding * 2;
        int height = fr.FONT_HEIGHT + padding * 2;

        // background (semi‐transparent black)
        Gui.drawRect(x, y, x + width, y + height, 0xB0000000);

        // border color
        int border = 0xFFAAAAAA;
        // top
        Gui.drawRect(x, y, x + width, y + 1, border);
        // bottom
        Gui.drawRect(x, y + height - 1, x + width, y + height, border);
        // left
        Gui.drawRect(x, y, x + 1, y + height, border);
        // right
        Gui.drawRect(x + width - 1, y, x + width, y + height, border);

        // draw text
        fr.drawStringWithShadow(text, x + padding, y + padding, 0xFFFFFF);
    }
}