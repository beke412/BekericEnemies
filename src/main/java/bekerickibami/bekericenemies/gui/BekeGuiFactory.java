package bekerickibami.bekericenemies.gui;

import bekerickibami.bekericenemies.BekericEnemies;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

import java.util.HashSet;
import java.util.Set;

public class BekeGuiFactory implements IModGuiFactory {
    @Override
    public void initialize(Minecraft minecraft) {}

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new BekeGuiConfig(parentScreen);
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return new HashSet<>();
    }

    public static class BekeGuiConfig extends GuiConfig {
        public BekeGuiConfig(GuiScreen screen) {
            super(screen, (new ConfigElement(BekericEnemies.config.getCategory("main"))).getChildElements(), "bekericenemies",  false, false, "bekericenemies");
        }

        @Override
        public void onGuiClosed() {
            super.onGuiClosed();
            BekericEnemies.syncConfig();
        }
    }
}
