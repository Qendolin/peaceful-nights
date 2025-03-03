//? if fabric {
package com.qendolin.peacefulnight.platforms.fabric;

import com.qendolin.peacefulnight.ModPlatform;
import net.fabricmc.api.ModInitializer;
import com.qendolin.peacefulnight.PeacefulNightInit;
import net.fabricmc.loader.api.FabricLoader;

public class PeacefulNightFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		PeacefulNightInit.entrypoint(new FabricPlatform());
	}

	public static class FabricPlatform implements ModPlatform{

		@Override
		public String getModloader() {
			return "Fabric";
		}

		@Override
		public boolean isModLoaded(String modloader) {
			return FabricLoader.getInstance().isModLoaded(modloader);
		}
	}
}
//?}