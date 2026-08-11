package com.euphony.soul_fire_burning.mixin;

import com.euphony.soul_fire_burning.api.SoulFireAccess;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {
    /**
     * Burning arrows that are soul-fire marked transfer soul fire to their target.
     */
    @Inject(
            method = "onHitEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;igniteForSeconds(F)V",
                    shift = At.Shift.AFTER))
    private void soulFireBurning$propagateOnHit(EntityHitResult hitResult, CallbackInfo ci) {
        Entity arrow = (Entity) (Object) this;
        if (!arrow.level().isClientSide() && SoulFireAccess.isSoulFire(arrow)) {
            SoulFireAccess.setSoulFire(hitResult.getEntity(), true);
        }
    }
}
