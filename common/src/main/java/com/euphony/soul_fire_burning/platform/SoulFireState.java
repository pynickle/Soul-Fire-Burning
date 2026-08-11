package com.euphony.soul_fire_burning.platform;

import net.minecraft.world.entity.Entity;

/**
 * Platform-backed soul-fire flag storage (Fabric / NeoForge data attachments).
 */
public interface SoulFireState {
    boolean isSoulFire(Entity entity);

    void setSoulFire(Entity entity, boolean soulFire);
}
