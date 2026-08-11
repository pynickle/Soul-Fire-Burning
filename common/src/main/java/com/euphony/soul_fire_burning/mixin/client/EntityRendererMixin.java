package com.euphony.soul_fire_burning.mixin.client;

import com.euphony.soul_fire_burning.api.SoulFireAccess;
import com.euphony.soul_fire_burning.api.SoulFireRenderState;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {
    @Inject(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;displayFireAnimation()Z",
                    shift = At.Shift.AFTER))
    private void soulFireBurning$extractSoulFire(
            Entity entity, EntityRenderState state, float partialTicks, CallbackInfo ci) {
        ((SoulFireRenderState) state).soulFireBurning$setSoulFire(SoulFireAccess.isSoulFire(entity));
    }
}
