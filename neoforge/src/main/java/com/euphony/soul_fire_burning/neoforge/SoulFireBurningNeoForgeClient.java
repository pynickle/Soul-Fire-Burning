package com.euphony.soul_fire_burning.neoforge;

import com.euphony.soul_fire_burning.SoulFireBurning;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = SoulFireBurning.MOD_ID, dist = Dist.CLIENT)
public final class SoulFireBurningNeoForgeClient {
    public SoulFireBurningNeoForgeClient() {
        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (client, parent) -> SoulFireBurning.config.makeScreen(parent));
    }
}
