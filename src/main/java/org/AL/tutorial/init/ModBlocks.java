package org.AL.tutorial.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.AL.tutorial.Tutorial;
import org.AL.tutorial.block.machine.IndustrialProcessingUnitBlock;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Tutorial.MODID);

    public static final RegistryObject<Block> RAW_MATERIAL_BLOCK =
            blockRegister("raw_material_block",
                    () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> INDUSTRIAL_PROCESSING_UNIT =
            blockRegister("industrial_processing_unit", IndustrialProcessingUnitBlock::new);

    public static <T extends Block> RegistryObject<T> blockRegister(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        blockItemRegister(name, toReturn);

        return toReturn;
    }

    public static <T extends Block> RegistryObject<Item> blockItemRegister(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
