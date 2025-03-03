package com.qendolin.peacefulnights.compat;

import com.qendolin.peacefulnights.PeacefulNightsInit;
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class EnhancedCelestialsCompat {

    public static final boolean IS_LOADED = PeacefulNightsInit.PLATFORM.isModLoaded("enhancedcelestials");

    private static EnhancedCelestialsCompat instance;

    private static void init() {
        instance = IS_LOADED ? new EnhancedCelestialsCompatImpl() : new EnhancedCelestialsCompatStub();
    }

    static {
        init();
    }

    public static EnhancedCelestialsCompat instance() {
        return instance;
    }

    public abstract boolean isBloodMoon(ServerLevelAccessor level);
}
