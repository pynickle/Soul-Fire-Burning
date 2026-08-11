package com.euphony.soul_fire_burning.mixin;

import com.euphony.soul_fire_burning.api.SoulFireAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseFireBlock.class)
public abstract class BaseFireBlockMixin {
    /**
     * After vanilla FIRE_IGNITE, set or clear the soul-fire mark based on the fire block type.
     * Standing in regular fire clears the mark; soul fire applies it.
     */
    @Inject(method = "entityInside", at = @At("HEAD"))
    private void soulFireBurning$markSoulFire(
            BlockState state,
            Level level,
            BlockPos pos,
            Entity entity,
            InsideBlockEffectApplier effectApplier,
            boolean isPrecise,
            CallbackInfo ci) {
        if (level.isClientSide()) {
            return;
        }
        boolean soul = (Object) this instanceof SoulFireBlock;
        effectApplier.runAfter(InsideBlockEffectType.FIRE_IGNITE, e -> SoulFireAccess.setSoulFire(e, soul));
    }
}
