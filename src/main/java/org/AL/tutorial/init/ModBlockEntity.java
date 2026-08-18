package org.AL.tutorial.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.AL.tutorial.Tutorial;
import org.AL.tutorial.blockEntity.IndustrialProcessingUnitBlockEntity;

public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Tutorial.MODID);

    public static final RegistryObject<BlockEntityType<IndustrialProcessingUnitBlockEntity>>
            INDUSTRIAL_PROCESSING_UNIT_BE =
            BLOCK_ENTITIES.register("industrial_processing_unit_be",
                    () -> BlockEntityType.Builder.of(
                            IndustrialProcessingUnitBlockEntity::new,
                            ModBlocks.INDUSTRIAL_PROCESSING_UNIT.get()
                    ).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
