package com.euphony.soul_fire_burning.config;

import com.euphony.soul_fire_burning.SoulFireBurning;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.gui.screens.Screen;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Server-authoritative gameplay config (also used in singleplayer).
 * Defaults keep vanilla damage values; optional enhancements are opt-in.
 */
public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static final ModConfig DEFAULTS = new ModConfig();

    /**
     * When true, entities burning with soul fire take {@code 2} damage per second
     * instead of vanilla {@code 1} during continuous burning ({@code Entity#baseTick}).
     */
    public boolean enhancedSoulFireDamage = false;

    /**
     * Soul Fire'd–style campfire handling (no ignition). When true, lit soul
     * campfire contact marks the entity as soul-fire burning; regular campfires
     * clear the mark. Vanilla still never sets entities on fire from campfires.
     */
    public boolean soulCampfireEffects = false;

    private transient Path configPath;

    public static ModConfig load(Path configDir) {
        Path path = configDir.resolve(SoulFireBurning.MOD_ID + ".json");
        ModConfig config = new ModConfig();
        config.configPath = path;

        if (Files.exists(path)) {
            try (Reader reader = Files.newBufferedReader(path)) {
                ModConfig loaded = GSON.fromJson(reader, ModConfig.class);
                if (loaded != null) {
                    config.enhancedSoulFireDamage = loaded.enhancedSoulFireDamage;
                    config.soulCampfireEffects = loaded.soulCampfireEffects;
                }
            } catch (Exception e) {
                SoulFireBurning.LOGGER.error("Failed to load config from {}, using defaults", path, e);
            }
        }

        config.save();
        return config;
    }

    public void save() {
        if (configPath == null) {
            return;
        }
        try {
            Files.createDirectories(configPath.getParent());
            try (Writer writer = Files.newBufferedWriter(configPath)) {
                GSON.toJson(this, writer);
            }
        } catch (IOException e) {
            SoulFireBurning.LOGGER.error("Failed to save config to {}", configPath, e);
        }
    }

    public Screen makeScreen(Screen parent) {
        return ModConfigScreen.create(parent, this);
    }
}
