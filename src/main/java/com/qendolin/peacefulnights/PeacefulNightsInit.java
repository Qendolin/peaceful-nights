package com.qendolin.peacefulnights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeacefulNightsInit {
    public static final String MODID = "peaceful_nights";
    public static final Logger LOGGER = LoggerFactory.getLogger("Peaceful Nights");
    public static ModPlatform PLATFORM = null;

    public static void entrypoint(ModPlatform platform) {
        PeacefulNightsInit.PLATFORM = platform;
        PeacefulNightsGameRules.init();
    }
}