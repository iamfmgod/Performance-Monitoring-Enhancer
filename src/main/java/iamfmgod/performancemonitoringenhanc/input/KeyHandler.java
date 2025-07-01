package iamfmgod.performancemonitoringenhanc.input;

import iamfmgod.performancemonitoringenhanc.gui.HudDragScreen;
import iamfmgod.performancemonitoringenhanc.gui.HudSettingsGui;
import iamfmgod.performancemonitoringenhanc.hud.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

@SideOnly(Side.CLIENT)
public class KeyHandler {
    public static KeyBinding toggleHudKey;
    public static KeyBinding toggleMouseKey;
    public static KeyBinding openSettingsKey;

    public static void init() {
        toggleHudKey    = new KeyBinding("Toggle HUD Overlay", Keyboard.KEY_H, "Performance Enhancer");
        toggleMouseKey  = new KeyBinding("Toggle HUD Drag Mode", Keyboard.KEY_G, "Performance Enhancer");
        openSettingsKey = new KeyBinding("Open HUD Settings", Keyboard.KEY_K, "Performance Enhancer");

        ClientRegistry.registerKeyBinding(toggleHudKey);
        ClientRegistry.registerKeyBinding(toggleMouseKey);
        ClientRegistry.registerKeyBinding(openSettingsKey);

        MinecraftForge.EVENT_BUS.register(new KeyHandler());
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();

        if (toggleHudKey.isPressed()) {
            HudManager.toggleVisibility();
        }

        if (toggleMouseKey.isPressed()) {
            HudManager.toggleMouseEnabled();
            if (HudManager.isMouseEnabled()) {
                Mouse.setGrabbed(false);
                mc.displayGuiScreen(new HudDragScreen());
            } else {
                Mouse.setGrabbed(true);
                mc.displayGuiScreen(null);
            }
        }

        if (openSettingsKey.isPressed()) {
            mc.displayGuiScreen(new HudSettingsGui());
        }
    }
}