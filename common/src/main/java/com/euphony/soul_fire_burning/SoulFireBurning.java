package com.euphony.soul_fire_burning;

import com.euphony.soul_fire_burning.config.ModConfig;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.nio.file.Path;

public final class SoulFireBurning {
    public static final String MOD_ID = "soul_fire_burning";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ModConfig config = new ModConfig();

    private SoulFireBurning() {
    }

    public static void init(Path configDir) {
        config = ModConfig.load(configDir);
        LOGGER.info("Soul Fire Burning initialized");
    }
}
