package com.euphony.soul_fire_burning.api;

import com.euphony.soul_fire_burning.platform.SoulFireState;
import net.minecraft.world.entity.Entity;

/**
 * Dual-loader access to an entity's soul-fire burning state.
 * Backed by each loader's recommended data-attachment API (not SynchedEntityData).
 */
public final class SoulFireAccess {
    public static final float ENHANCED_DAMAGE = 2.0F;

    private static SoulFireState state = new SoulFireState() {
        @Override
        public boolean isSoulFire(Entity entity) {
            return false;
        }

        @Override
        public void setSoulFire(Entity entity, boolean soulFire) {
            // no-op until platform init
        }
    };

    private SoulFireAccess() {
    }

    public static void init(SoulFireState platformState) {
        state = platformState;
    }

    public static boolean isSoulFire(Entity entity) {
        return state.isSoulFire(entity);
    }

    public static void setSoulFire(Entity entity, boolean soulFire) {
        if (entity.fireImmune()) {
            return;
        }
        state.setSoulFire(entity, soulFire);
    }
}
