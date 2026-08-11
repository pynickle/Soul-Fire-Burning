package com.euphony.soul_fire_burning.mixin.client;

import com.euphony.soul_fire_burning.api.SoulFireRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityRenderState.class)
public class EntityRenderStateMixin implements SoulFireRenderState {
    @Unique
    private boolean soulFireBurning$soulFire;

    @Override
    public boolean soulFireBurning$isSoulFire() {
        return this.soulFireBurning$soulFire;
    }

    @Override
    public void soulFireBurning$setSoulFire(boolean soulFire) {
        this.soulFireBurning$soulFire = soulFire;
    }
}
