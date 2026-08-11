package com.euphony.soul_fire_burning.mixin.client;

import com.euphony.soul_fire_burning.api.SoulFireAccess;
import com.euphony.soul_fire_burning.client.SoulFireSprites;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ScreenEffectRenderer.class)
public abstract class ScreenEffectRendererMixin {
    @Final
    @Shadow
    private Minecraft minecraft;

    /**
     * First-person fire overlay uses soul-fire texture when the player is soul-fire marked.
     */
    @Redirect(
            method = "submit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/resources/model/sprite/SpriteGetter;get(Lnet/minecraft/client/resources/model/sprite/SpriteId;)Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"))
    private TextureAtlasSprite soulFireBurning$swapFireSprite(SpriteGetter instance, SpriteId spriteId) {
        if (spriteId.equals(ModelBakery.FIRE_1)
                && this.minecraft.player != null
                && SoulFireAccess.isSoulFire(this.minecraft.player)) {
            return instance.get(SoulFireSprites.SOUL_FIRE_1);
        }
        return instance.get(spriteId);
    }
}
