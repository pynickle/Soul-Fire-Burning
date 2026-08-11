package com.euphony.soul_fire_burning.fabric;

import com.euphony.soul_fire_burning.SoulFireBurning;
import com.euphony.soul_fire_burning.api.SoulFireAccess;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public final class SoulFireBurningFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // Class-init registers the Fabric attachment type.
        SoulFireAccess.init(FabricSoulFireState.INSTANCE);
        SoulFireBurning.init(FabricLoader.getInstance().getConfigDir());
    }
}
