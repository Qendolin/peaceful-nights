//? if forge {
/*package com.qendolin.peacefulnight.platforms.forge;

import com.qendolin.peacefulnight.ConfigScreen;
import com.qendolin.peacefulnight.PeacefulNightInit;
import com.qendolin.peacefulnight.ModPlatform;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod("peaceful_night")
public class PeacefulNightForge {
	public PeacefulNightForge() {
		PeacefulNightInit.entrypoint(new ForgePlatform());
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