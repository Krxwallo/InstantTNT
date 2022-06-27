package com.justAm0dd3r.instant_tnt;

import com.justAm0dd3r.instant_tnt.config.Config;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

/**
 * Author: justAm0dd3r
 */
@Mod(InstantTNT.MOD_ID)
public class InstantTNT {
    public static final String MOD_ID = "instant_tnt";
    public InstantTNT() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC, MOD_ID + "-common.toml");
    }
}
