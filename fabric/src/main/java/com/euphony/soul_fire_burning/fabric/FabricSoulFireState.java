package com.euphony.soul_fire_burning.fabric;

import com.euphony.soul_fire_burning.SoulFireBurning;
import com.euphony.soul_fire_burning.platform.SoulFireState;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

/**
 * Fabric data attachments (persistent + auto-synced to tracking clients).
 */
@SuppressWarnings("UnstableApiUsage")
public final class FabricSoulFireState implements SoulFireState {
    public static final AttachmentType<Boolean> SOUL_FIRE = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(SoulFireBurning.MOD_ID, "soul_fire"),
            builder -> builder
                    .initializer(() -> Boolean.FALSE)
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all()));

    public static final FabricSoulFireState INSTANCE = new FabricSoulFireState();

    private FabricSoulFireState() {
    }

    @Override
    public boolean isSoulFire(Entity entity) {
        return Boolean.TRUE.equals(entity.getAttachedOrElse(SOUL_FIRE, Boolean.FALSE));
    }

    @Override
    public void setSoulFire(Entity entity, boolean soulFire) {
        boolean current = isSoulFire(entity);
        if (current == soulFire) {
            return;
        }
        if (soulFire) {
            entity.setAttached(SOUL_FIRE, Boolean.TRUE);
        } else {
            // Prefer remove so we don't keep a default-false attachment forever.
            entity.removeAttached(SOUL_FIRE);
        }
    }
}
