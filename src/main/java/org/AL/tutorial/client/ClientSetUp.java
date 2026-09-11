package org.AL.tutorial.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.AL.tutorial.Tutorial;
import org.AL.tutorial.container.screen.IndustrialProcessingUnitScreen;
import org.AL.tutorial.init.ModMenuTypes;

@Mod.EventBusSubscriber(modid = Tutorial.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetUp {

    @SubscribeEvent
    public static void onClientSetUp(FMLClientSetupEvent event) {
        registerScreens();
    }

    private static void registerScreens() {
        MenuScreens.register(
                ModMenuTypes.INDUSTRIAL_PROCESSING_UNIT_MENU.get(),
                IndustrialProcessingUnitScreen::new
        );
    }

}
