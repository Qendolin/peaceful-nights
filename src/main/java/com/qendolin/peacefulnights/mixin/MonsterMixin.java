package com.qendolin.peacefulnights.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.qendolin.peacefulnights.PeacefulNightsGameRules;
import com.qendolin.peacefulnights.compat.EnhancedCelestialsCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Monster.class)
public class MonsterMixin {

    @Unique
    private static final ThreadLocal<EntityType<? extends Monster>> typeArg = new ThreadLocal<>();

    @Unique
    @Redirect(method = "isDarkEnoughToSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/ServerLevelAccessor;getMaxLocalRawBrightness(Lnet/minecraft/core/BlockPos;)I"))
    private static int onGetMaxLocalRawBrightness(ServerLevelAccessor level, BlockPos blockPos) {
        return peacefulnights$getMaxLocalRawBrightnessOverride(level, blockPos, null);
    }

    @Unique
    @Redirect(method = "isDarkEnoughToSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/ServerLevelAccessor;getMaxLocalRawBrightness(Lnet/minecraft/core/BlockPos;I)I"))
    private static int onGetMaxLocalRawBrightness(ServerLevelAccessor level, BlockPos blockPos, int amount) {
        return peacefulnights$getMaxLocalRawBrightnessOverride(level, blockPos, amount);
    }

    @Unique
    private static int peacefulnights$getMaxLocalRawBrightnessOverride(ServerLevelAccessor level, BlockPos blockPos, Integer amount) {
        EntityType<? extends Monster> type = typeArg.get();
        boolean override = true;
        override &= !level.getLevel().getGameRules().getBoolean(PeacefulNightsGameRules.DO_SURFACE_SPAWNING);
        override &= level.dimensionType().hasSkyLight();
        override &= !EnhancedCelestialsCompat.instance().isBloodMoon(level);
        if (type != null) {
            override &= !type.getCategory().isFriendly();
        }

        if(override)
            return level.getMaxLocalRawBrightness(blockPos, 0);

        if(amount == null)
            return level.getMaxLocalRawBrightness(blockPos);
        else
            return level.getMaxLocalRawBrightness(blockPos, amount);

    }

    @Unique
    @WrapOperation(method = "checkMonsterSpawnRules", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Monster;isDarkEnoughToSpawn(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)Z"))
    private static boolean onCheckMonsterSpawnRules(ServerLevelAccessor level, BlockPos pos, RandomSource random, Operation<Boolean> original, EntityType<? extends Monster> type) {
        typeArg.set(type);
        boolean result = original.call(level, pos, random);
        typeArg.remove();
        return result;
    }
}
