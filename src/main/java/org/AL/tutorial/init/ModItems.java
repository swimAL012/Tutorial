package org.AL.tutorial.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.AL.tutorial.Tutorial;

public class ModItems {

    public static final DeferredRegister<Item> ITEM =
            DeferredRegister.create(ForgeRegistries.ITEMS, Tutorial.MODID);

    public static final RegistryObject<Item> RAW_MATERIAL =
            ITEM.register("raw_material",
                    () -> new Item(new Item.Properties()));

    public static final RegistryObject<BlockItem> RAW_MATERIAL_BLOCK =
            ITEM.register("raw_material_block",
                    () -> new BlockItem(ModBlocks.RAW_MATERIAL_BLOCK.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEM.register(eventBus);
    }

}
