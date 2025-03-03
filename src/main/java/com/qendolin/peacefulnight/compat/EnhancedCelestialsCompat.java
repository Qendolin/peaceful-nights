package com.qendolin.peacefulnight.compat;

import com.qendolin.peacefulnight.PeacefulNightInit;
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class EnhancedCelestialsCompat {

    public static final boolean IS_LOADED = PeacefulNightInit.PLATFORM.isModLoaded("enhancedcelestials");

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
