package com.qendolin.peacefulnights.compat;

import net.minecraft.world.level.ServerLevelAccessor;

public class EnhancedCelestialsCompatStub extends EnhancedCelestialsCompat {
    @Override
    public boolean isBloodMoon(ServerLevelAccessor level) {
        return false;
    }
}
