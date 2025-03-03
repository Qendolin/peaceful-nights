//? if fabric {
package com.qendolin.peacefulnight.platforms.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.qendolin.peacefulnight.ConfigScreen;

public class ModMenuIntegration implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ConfigScreen::createConfigScreen;
    }
}
//?}
