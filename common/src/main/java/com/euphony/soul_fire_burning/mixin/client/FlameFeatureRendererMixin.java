package com.euphony.soul_fire_burning.mixin.client;

import com.euphony.soul_fire_burning.api.SoulFireRenderState;
import com.euphony.soul_fire_burning.client.SoulFireSprites;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.feature.FlameFeatureRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FlameFeatureRenderer.class)
public abstract class FlameFeatureRendererMixin {
    @ModifyVariable(method = "renderFlame", at = @At("STORE"), ordinal = 0)
    private TextureAtlasSprite soulFireBurning$sprite0(
            TextureAtlasSprite original,
            PoseStack.Pose pose,
            MultiBufferSource bufferSource,
            EntityRenderState state,
            Quaternionf rotation,
            AtlasManager atlasManager) {
        if (state instanceof SoulFireRenderState soul && soul.soulFireBurning$isSoulFire()) {
            return atlasManager.get(SoulFireSprites.SOUL_FIRE_0);
        }
        return original;
    }

    @ModifyVariable(method = "renderFlame", at = @At("STORE"), ordinal = 1)
    private TextureAtlasSprite soulFireBurning$sprite1(
            TextureAtlasSprite original,
            PoseStack.Pose pose,
            MultiBufferSource bufferSource,
            EntityRenderState state,
            Quaternionf rotation,
            AtlasManager atlasManager) {
        if (state instanceof SoulFireRenderState soul && soul.soulFireBurning$isSoulFire()) {
            return atlasManager.get(SoulFireSprites.SOUL_FIRE_1);
        }
        return original;
    }
}
