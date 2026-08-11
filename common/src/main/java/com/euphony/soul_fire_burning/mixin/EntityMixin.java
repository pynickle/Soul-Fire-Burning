package com.euphony.soul_fire_burning.mixin;

import com.euphony.soul_fire_burning.SoulFireBurning;
import com.euphony.soul_fire_burning.api.SoulFireAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Gameplay hooks only. Soul-fire flag storage lives in platform data attachments.
 */
@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    private Level level;

    @Shadow
    public abstract int getRemainingFireTicks();

    /**
     * Continuous burn damage: optionally 2 HP/s while marked as soul fire.
     */
    @Redirect(
            method = "baseTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private boolean soulFireBurning$redirectBurnDamage(
            Entity instance, ServerLevel level, DamageSource source, float damage) {
        float actual = damage;
        if (SoulFireBurning.config.enhancedSoulFireDamage && SoulFireAccess.isSoulFire(instance)) {
            actual = SoulFireAccess.ENHANCED_DAMAGE;
        }
        return instance.hurtServer(level, source, actual);
    }

    /**
     * Clear soul-fire mark when extinguished, or when fire is refreshed so a new
     * source can re-apply the correct type (Soul Fire'd style).
     */
    @Inject(method = "setRemainingFireTicks", at = @At("HEAD"))
    private void soulFireBurning$onSetRemainingFireTicks(int remainingTicks, CallbackInfo ci) {
        if (this.level.isClientSide()) {
            return;
        }
        if (remainingTicks <= 0 || remainingTicks >= this.getRemainingFireTicks()) {
            SoulFireAccess.setSoulFire((Entity) (Object) this, false);
        }
    }
}
