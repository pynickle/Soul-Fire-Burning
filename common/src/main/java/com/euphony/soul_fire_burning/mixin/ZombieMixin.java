package com.euphony.soul_fire_burning.mixin;

import com.euphony.soul_fire_burning.api.SoulFireAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zombie.class)
public abstract class ZombieMixin {
    /**
     * Burning zombies on soul fire can set their attack targets on soul fire too.
     */
    @Inject(
            method = "doHurtTarget",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;igniteForSeconds(F)V",
                    shift = At.Shift.AFTER))
    private void soulFireBurning$propagateOnAttack(
            ServerLevel level, Entity target, CallbackInfoReturnable<Boolean> cir) {
        Entity zombie = (Entity) (Object) this;
        if (SoulFireAccess.isSoulFire(zombie)) {
            SoulFireAccess.setSoulFire(target, true);
        }
    }
}
