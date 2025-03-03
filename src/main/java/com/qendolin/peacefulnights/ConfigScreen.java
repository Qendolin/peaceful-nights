package com.qendolin.peacefulnights;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends Screen {

    public ConfigScreen(Screen parent) {
        super(Component.empty());
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredString(minecraft.font,
                "Work in Progress",
                width / 2,
                height / 2,
                0xFFFFFFFF);
    }

    public static ConfigScreen createConfigScreen(Screen parent) {
        return new ConfigScreen(parent);
    }
}
