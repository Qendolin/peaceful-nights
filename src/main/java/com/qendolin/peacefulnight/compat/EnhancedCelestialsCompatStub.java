package com.qendolin.peacefulnight.compat;

import net.minecraft.world.level.ServerLevelAccessor;

public class EnhancedCelestialsCompatStub extends EnhancedCelestialsCompat {
    @Override
    public boolean isBloodMoon(ServerLevelAccessor level) {
        return false;
    }
}
