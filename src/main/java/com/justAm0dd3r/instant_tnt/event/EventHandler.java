package com.justAm0dd3r.instant_tnt.event;

import net.minecraft.block.BlockState;
import net.minecraft.block.TNTBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
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
            // Make it explode
            tntBlock.catchFire(evt.getPlacedBlock(), evt.getWorld().getWorld(), evt.getPos(), null, null);
            tntBlock.removedByPlayer(evt.getPlacedBlock(),
                    evt.getWorld().getWorld(), evt.getPos(), (PlayerEntity)evt.getEntity(), false, Fluids.EMPTY.getDefaultState());
            LOGGER.info("Made TNT catch fire.");
        }
    }
}
