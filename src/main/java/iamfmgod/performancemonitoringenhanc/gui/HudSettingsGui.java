package iamfmgod.performancemonitoringenhanc.gui;

import iamfmgod.performancemonitoringenhanc.config.HudConfig;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiCheckBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HudSettingsGui extends GuiScreen {
    private final List<GuiCheckBox> checkboxes = new ArrayList<>();
    private GuiButton doneButton;

    @Override
    public void initGui() {
        // reload from disk so checkboxes reflect last-saved state
        HudConfig.sync();

        int startY = this.height / 4;
        int x = this.width / 4;
        int id = 0;

        checkboxes.clear();
        checkboxes.add(new GuiCheckBox(id++, x, startY +  0, "Show FPS",            HudConfig.SHOW_FPS));
        checkboxes.add(new GuiCheckBox(id++, x, startY + 20, "Show 1% Low",         HudConfig.SHOW_ONE_PERCENT_LOW));
        checkboxes.add(new GuiCheckBox(id++, x, startY + 40, "Show 0.01% Low",      HudConfig.SHOW_ZERO_ZERO_ONE_LOW));
        checkboxes.add(new GuiCheckBox(id++, x, startY + 60, "Show TPS",            HudConfig.SHOW_TPS));
        checkboxes.add(new GuiCheckBox(id++, x, startY + 80, "Show Ping",           HudConfig.SHOW_PING));
        this.buttonList.addAll(checkboxes);

        doneButton = new GuiButton(id, x, startY + 120, 100, 20, "Done");
        this.buttonList.add(doneButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == doneButton) {
            // close the GUI; onGuiClosed() will handle saving
            this.mc.displayGuiScreen(null);
            return;
        }
        if (button instanceof GuiCheckBox) {
            GuiCheckBox cb = (GuiCheckBox) button;
            // update in-memory flags immediately
            switch (cb.id) {
                case 0: HudConfig.SHOW_FPS               = cb.isChecked(); break;
                case 1: HudConfig.SHOW_ONE_PERCENT_LOW   = cb.isChecked(); break;
                case 2: HudConfig.SHOW_ZERO_ZERO_ONE_LOW = cb.isChecked(); break;
                case 3: HudConfig.SHOW_TPS               = cb.isChecked(); break;
                case 4: HudConfig.SHOW_PING              = cb.isChecked(); break;
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, "HUD Settings", width / 2, height / 4 - 20, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onGuiClosed() {
        // persist everything to disk
        HudConfig.save();
    }
}