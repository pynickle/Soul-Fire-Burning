package com.euphony.soul_fire_burning.neoforge;

import com.euphony.soul_fire_burning.SoulFireBurning;
import com.euphony.soul_fire_burning.api.SoulFireAccess;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;

@Mod(SoulFireBurning.MOD_ID)
public final class SoulFireBurningNeoForge {
    public SoulFireBurningNeoForge(IEventBus modBus) {
        NeoForgeSoulFireState.register(modBus);
        SoulFireAccess.init(NeoForgeSoulFireState.INSTANCE);
        SoulFireBurning.init(FMLPaths.CONFIGDIR.get());
    }
}
