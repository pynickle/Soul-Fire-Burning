package com.euphony.soul_fire_burning.mixin.client;

import com.euphony.soul_fire_burning.api.SoulFireRenderState;
import com.euphony.soul_fire_burning.client.SoulFireSprites;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.feature.FlameFeatureRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FlameFeatureRenderer.class)
public abstract class FlameFeatureRendererMixin {
    @ModifyVariable(method = "prepare", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private TextureAtlasSprite soulFireBurning$sprite0(
            TextureAtlasSprite original,
            FlameFeatureRenderer.Submit submit,
            VertexConsumer buffer,
            TextureAtlasSprite fire1,
            TextureAtlasSprite fire2) {
        return soulFireBurning$resolve(original, submit, true);
    }

    @ModifyVariable(method = "prepare", at = @At("HEAD"), argsOnly = true, ordinal = 1)
    private TextureAtlasSprite soulFireBurning$sprite1(
            TextureAtlasSprite original,
            FlameFeatureRenderer.Submit submit,
            VertexConsumer buffer,
            TextureAtlasSprite fire1,
            TextureAtlasSprite fire2) {
        return soulFireBurning$resolve(original, submit, false);
    }

    @Unique
    private static TextureAtlasSprite soulFireBurning$resolve(
            TextureAtlasSprite original, FlameFeatureRenderer.Submit submit, boolean first) {
        if (submit.entityRenderState() instanceof SoulFireRenderState state && state.soulFireBurning$isSoulFire()) {
            AtlasManager atlas = Minecraft.getInstance().getAtlasManager();
            return atlas.get(first ? SoulFireSprites.SOUL_FIRE_0 : SoulFireSprites.SOUL_FIRE_1);
        }
        return original;
    }
}
