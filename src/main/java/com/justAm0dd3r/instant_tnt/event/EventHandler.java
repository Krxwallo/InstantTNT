package com.justAm0dd3r.instant_tnt.event;

import com.justAm0dd3r.instant_tnt.config.Config;
import com.justAm0dd3r.instant_tnt.reference.Reference;
import net.minecraft.block.TNTBlock;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
        if (evt.getPlacedBlock().getBlock() instanceof TNTBlock && evt.getEntity() instanceof PlayerEntity) {
            // Is a tnt block.
            TNTBlock tntBlock = (TNTBlock) evt.getPlacedBlock().getBlock();
            // Remove it
            tntBlock.removedByPlayer(evt.getPlacedBlock(),
                    evt.getWorld().getWorld(), evt.getPos(), (PlayerEntity)evt.getEntity(), false, Fluids.EMPTY.getDefaultState());
            World worldIn = evt.getWorld().getWorld();
            BlockPos pos = evt.getPos();
            TNTEntity tntEntity = null;
            if (!worldIn.isRemote) {
                // Create a tnt entity.
                tntEntity = new TNTEntity(worldIn, (double)pos.getX() + 0.5D, pos.getY(),
                        (double)pos.getZ() + 0.5D, null);
                // Set the charge duration.
                tntEntity.setFuse((int)(Config.COMMON.tntChargeDuration.get() * 10));
                worldIn.addEntity(tntEntity);
                worldIn.playSound(null, tntEntity.getPosX(), tntEntity.getPosY(), tntEntity.getPosZ(),
                        SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            if (tntEntity == null) LOGGER.warn("tntEntity is null.");
            else LOGGER.debug("Made TNT catch fire.");
        }
    }
}
