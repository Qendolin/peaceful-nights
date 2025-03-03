//? if fabric {
package com.qendolin.peacefulnights.platforms.fabric;

import com.qendolin.peacefulnights.ModPlatform;
import net.fabricmc.api.ModInitializer;
import com.qendolin.peacefulnights.PeacefulNightsInit;
import net.fabricmc.loader.api.FabricLoader;

public class PeacefulNightsFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		PeacefulNightsInit.entrypoint(new FabricPlatform());
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