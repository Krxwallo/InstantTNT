package io.github.krxwallo.instant_tnt;

import io.github.krxwallo.instant_tnt.config.Config;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

/**
 * Author: Krxwallo
 */
@Mod(InstantTNT.MOD_ID)
public class InstantTNT {
    public static final String MOD_ID = "instant_tnt";
    public InstantTNT() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC, MOD_ID + "-common.toml");
    }
}
