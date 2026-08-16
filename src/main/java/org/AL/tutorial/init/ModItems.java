package org.AL.tutorial.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.AL.tutorial.Tutorial;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModItems {

    public static final DeferredRegister<Item> ITEM =
            DeferredRegister.create(ForgeRegistries.ITEMS, Tutorial.MODID);

    public static final RegistryObject<Item> RAW_MATERIAL =
            registerItem("raw_material");

    private static RegistryObject<Item> registerItem(String name) {
        return ITEM.register(name, () -> new Item(new Item.Properties()));
    }

    private static RegistryObject<Item> registerItem(String name, Function<Item.Properties, Item> factory) {
        return ITEM.register(name,
                () -> factory.apply(new Item.Properties()));
    }

    private static RegistryObject<Item> registerItem(String name, Consumer<Item.Properties> propertiesModifier) {
        return ITEM.register(name,
                () -> {
                    Item.Properties props = new Item.Properties();
                    propertiesModifier.accept(props);
                    return new Item(props);
                });
    }

    public static void register(IEventBus eventBus) {
        ITEM.register(eventBus);
    }

}
