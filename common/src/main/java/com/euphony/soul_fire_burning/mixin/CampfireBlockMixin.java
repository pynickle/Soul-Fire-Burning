package com.euphony.soul_fire_burning.mixin;

import com.euphony.soul_fire_burning.SoulFireBurning;
import com.euphony.soul_fire_burning.api.SoulFireAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Soul Fire'd–style campfire handling: no ignition (vanilla never lights you from
 * campfires). When enabled, soul campfire contact damage marks the entity as
 * soul-fire burning so continuous burn / visuals stay consistent if already on fire.
 */
@Mixin(CampfireBlock.class)
public abstract class CampfireBlockMixin {
    @Redirect(
            method = "entityInside",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)V"))
    private void soulFireBurning$onCampfireHurt(
            Entity entity,
            DamageSource source,
            float damage,
            BlockState state,
            Level level,
            BlockPos pos,
            Entity entityArg,
            InsideBlockEffectApplier effectApplier,
            boolean isPrecise) {
        if (!level.isClientSide() && SoulFireBurning.config.soulCampfireEffects && state.getValue(CampfireBlock.LIT)) {
            if (state.is(Blocks.SOUL_CAMPFIRE)) {
                SoulFireAccess.setSoulFire(entity, true);
                // Match Soul Fire'd: soul-type campfire uses the fire type's damage when enhanced damage is on.
                if (SoulFireBurning.config.enhancedSoulFireDamage) {
                    damage = SoulFireAccess.ENHANCED_DAMAGE;
                }
            } else {
                SoulFireAccess.setSoulFire(entity, false);
            }
        }
        entity.hurt(source, damage);
    }
}
