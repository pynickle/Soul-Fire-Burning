package com.euphony.soul_fire_burning.fabric.integration;

import com.euphony.soul_fire_burning.SoulFireBurning;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public final class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> SoulFireBurning.config.makeScreen(parent);
    }
}
