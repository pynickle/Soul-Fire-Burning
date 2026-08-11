package com.euphony.soul_fire_burning.api;

/**
 * Implemented by {@link net.minecraft.client.renderer.entity.state.EntityRenderState} via mixin.
 */
public interface SoulFireRenderState {
    boolean soulFireBurning$isSoulFire();

    void soulFireBurning$setSoulFire(boolean soulFire);
}
