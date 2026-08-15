package org.AL.tutorial.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.AL.tutorial.Tutorial;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCK =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Tutorial.MODID);

    public static final RegistryObject<Block> RAW_MATERIAL_BLOCK =
            BLOCK.register("raw_material_block",
                    () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static void register(IEventBus eventBus) {
        BLOCK.register(eventBus);
    }

}
