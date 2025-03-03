package com.qendolin.peacefulnights;

import net.minecraft.world.level.GameRules;

public class PeacefulNightsGameRules {

    public static void init() {
    }

    public static final GameRules.Key<GameRules.BooleanValue> DO_SURFACE_SPAWNING
        = GameRules.register("doSurfaceSpawning", GameRules.Category.SPAWNING, GameRules.BooleanValue.create(false));

}
