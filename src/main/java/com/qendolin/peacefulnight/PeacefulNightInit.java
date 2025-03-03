package com.qendolin.peacefulnight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeacefulNightInit {
    public static final String MODID = "peaceful_night";
    public static final Logger LOGGER = LoggerFactory.getLogger("Peaceful Night");
    public static ModPlatform PLATFORM = null;

    public static void entrypoint(ModPlatform platform) {
        PeacefulNightInit.PLATFORM = platform;
        PeacefulNightGameRules.init();
    }
}