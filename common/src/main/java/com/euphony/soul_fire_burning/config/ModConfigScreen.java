package com.euphony.soul_fire_burning.config;

import com.euphony.soul_fire_burning.SoulFireBurning;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class ModConfigScreen {
    private ModConfigScreen() {
    }

    public static Screen create(Screen parent, ModConfig config) {
        Option<Boolean> enhancedDamage = Option.<Boolean>createBuilder()
                .name(optionName("enhancedSoulFireDamage"))
                .description(OptionDescription.of(optionDesc("enhancedSoulFireDamage")))
                .binding(
                        ModConfig.DEFAULTS.enhancedSoulFireDamage,
                        () -> config.enhancedSoulFireDamage,
                        value -> config.enhancedSoulFireDamage = value)
                .controller(TickBoxControllerBuilder::create)
                .build();

        Option<Boolean> soulCampfire = Option.<Boolean>createBuilder()
                .name(optionName("soulCampfireEffects"))
                .description(OptionDescription.of(optionDesc("soulCampfireEffects")))
                .binding(
                        ModConfig.DEFAULTS.soulCampfireEffects,
                        () -> config.soulCampfireEffects,
                        value -> config.soulCampfireEffects = value)
                .controller(TickBoxControllerBuilder::create)
                .build();

        return YetAnotherConfigLib.createBuilder()
                .title(Component.translatable("config." + SoulFireBurning.MOD_ID + ".title"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.translatable("config." + SoulFireBurning.MOD_ID + ".category.gameplay"))
                        .option(enhancedDamage)
                        .option(soulCampfire)
                        .build())
                .save(config::save)
                .build()
                .generateScreen(parent);
    }

    private static Component optionName(String key) {
        return Component.translatable("config." + SoulFireBurning.MOD_ID + ".option." + key);
    }

    private static Component optionDesc(String key) {
        return Component.translatable("config." + SoulFireBurning.MOD_ID + ".option." + key + ".desc");
    }
}
