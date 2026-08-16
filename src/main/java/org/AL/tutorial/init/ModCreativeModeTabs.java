package org.AL.tutorial.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.AL.tutorial.Tutorial;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tutorial.MODID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL =
            CREATIVE_MODE_TAB.register("tutorial",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(Items.STONE))
                            .title(Component.translatable("tab.tutorial"))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(ModItems.RAW_MATERIAL.get());
                                output.accept(ModBlocks.RAW_MATERIAL_BLOCK.get());
                                output.accept(ModBlocks.INDUSTRIAL_PROCESSING_UNIT.get());
                            })
                            .build()
            );


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
