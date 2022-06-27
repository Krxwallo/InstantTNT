package com.justAm0dd3r.instant_tnt.event;

import com.justAm0dd3r.instant_tnt.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber
public class EventHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent evt) {
        if (evt.getPlacedBlock().getBlock() instanceof TntBlock && evt.getEntity() instanceof Player) {
            // Is a tnt block.
            if (!evt.getEntity().isCrouching()) {
                TntBlock tntBlock = (TntBlock) evt.getPlacedBlock().getBlock();
                var server = evt.getWorld().getServer();
                if (server == null) {
                    LOGGER.error("Server is null in onBlockPlace event.");
                    return;
                }
                var world = server.overworld();
                // Remove it
                world.setBlockAndUpdate(evt.getPos(), Blocks.AIR.defaultBlockState());
                //tntBlock.playerWillDestroy(world, evt.getPos(), evt.getPlacedBlock(), (Player) evt.getEntity());
                BlockPos pos = evt.getPos();
                PrimedTnt tntEntity = null;
                if (!world.isClientSide) {
                    // Create a tnt entity at the position where the tnt block was.
                    tntEntity = new PrimedTnt(world, (double) pos.getX() + 0.5D, pos.getY(),
                            (double) pos.getZ() + 0.5D, (LivingEntity) evt.getEntity());
                    // Set the charge duration.
                    tntEntity.setFuse((int) (Config.COMMON.tntChargeDuration.get() * 10));

                    // Add the entity to the world.
                    world.addFreshEntity(tntEntity);

                    // Play the charge sound.
                    world.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(),
                            SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                if (tntEntity == null) LOGGER.warn("tntEntity is null.");
                else LOGGER.debug("Charged TNT. Charge Duration: " + Config.COMMON.tntChargeDuration.get() + " seconds");
            }
            else {
                // Player is sneaking. -> Ignore placing of tnt.
                LOGGER.debug("The Player is sneaking. Ignoring tnt.");
            }
        }
    }
}
