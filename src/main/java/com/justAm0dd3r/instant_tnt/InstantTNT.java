package com.justAm0dd3r.instant_tnt;

import com.justAm0dd3r.instant_tnt.config.Config;
import com.justAm0dd3r.instant_tnt.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Ref;
import java.util.stream.Collectors;

/**
 * Author: justAm0dd3r
 */
@Mod(Reference.MOD_ID)
public class InstantTNT {
    private static final Logger LOGGER = LogManager.getLogger();

    public InstantTNT() {
        LOGGER.info(Reference.MOD_NAME + " Version " + Reference.VERSION + " by " +
                Reference.AUTHOR + " started up.");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,
                Config.COMMON_SPEC, Reference.MOD_ID + "-common.toml");
    }
}
