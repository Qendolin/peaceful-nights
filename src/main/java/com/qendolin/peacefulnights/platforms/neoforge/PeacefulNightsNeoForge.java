//? if neoforge {
/*package com.qendolin.peacefulnights.platforms.neoforge;

import com.qendolin.peacefulnights.ConfigScreen;
import com.qendolin.peacefulnights.PeacefulNightsInit;
import com.qendolin.peacefulnights.ModPlatform;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
//? if <1.21 {
/^import net.neoforged.neoforge.client.ConfigScreenHandler;
^///?} else {
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
//?}
@Mod("peaceful_nights")
public class PeacefulNightsNeoForge {
	public PeacefulNightsNeoForge() {
		PeacefulNightsInit.entrypoint(new NeoForgePlatform());
//        ModLoadingContext.get().registerExtensionPoint(
//                //? if <1.21 {
//                /^ConfigScreenHandler.ConfigScreenFactory.class,
//                () -> new ConfigScreenHandler.ConfigScreenFactory(
//                        ((client, parent) -> ConfigScreen.createConfigScreen(parent))
//                )
//                ^///?} else {
//                IConfigScreenFactory.class,
//                () -> (client, parent) -> ConfigScreen.createConfigScreen(parent)
//                //?}
//        );
	}
    public static class NeoForgePlatform implements ModPlatform {
        @Override
        public String getModloader() {
            return "NeoForge";
        }

        @Override
        public boolean isModLoaded(String modId) {
            return ModList.get().isLoaded(modId);
        }
    }
}
*///?}