//? if forge {
/*package com.qendolin.peacefulnights.platforms.forge;

import com.qendolin.peacefulnights.ConfigScreen;
import com.qendolin.peacefulnights.PeacefulNightsInit;
import com.qendolin.peacefulnights.ModPlatform;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod("peaceful_nights")
public class PeacefulNightsForge {
	public PeacefulNightsForge() {
		PeacefulNightsInit.entrypoint(new ForgePlatform());
        // MinecraftForge.registerConfigScreen(ConfigScreen::createConfigScreen);
	}
	public static class ForgePlatform implements ModPlatform {
		@Override
		public String getModloader() {
			return "LexForge";
		}

		@Override
		public boolean isModLoaded(String modId) {
			return ModList.get().isLoaded(modId);
		}
	}

}
*///?}