package com.euphony.soul_fire_burning.neoforge;

import com.euphony.soul_fire_burning.SoulFireBurning;
import com.euphony.soul_fire_burning.platform.SoulFireState;
import com.mojang.serialization.Codec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

/**
 * NeoForge syncable data attachments (persistent + auto-synced).
 */
public final class NeoForgeSoulFireState implements SoulFireState {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, SoulFireBurning.MOD_ID);

    public static final Supplier<AttachmentType<Boolean>> SOUL_FIRE = ATTACHMENT_TYPES.register(
            "soul_fire",
            () -> AttachmentType.builder(() -> Boolean.FALSE)
                    .serialize(Codec.BOOL.fieldOf("soul_fire"))
                    .sync(ByteBufCodecs.BOOL)
                    .build());

    public static final NeoForgeSoulFireState INSTANCE = new NeoForgeSoulFireState();

    private NeoForgeSoulFireState() {
    }

    public static void register(IEventBus modBus) {
        ATTACHMENT_TYPES.register(modBus);
    }

    @Override
    public boolean isSoulFire(Entity entity) {
        return Boolean.TRUE.equals(entity.getData(SOUL_FIRE));
    }

    @Override
    public void setSoulFire(Entity entity, boolean soulFire) {
        boolean current = isSoulFire(entity);
        if (current == soulFire) {
            return;
        }
        if (soulFire) {
            entity.setData(SOUL_FIRE, Boolean.TRUE);
        } else {
            entity.removeData(SOUL_FIRE);
        }
    }
}
