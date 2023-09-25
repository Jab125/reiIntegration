//#if LOADER<=FORGE
package com.jab125.clothintegration;

import com.jab125.clothintegration.forge.test.TestConfig;
import com.jab125.clothintegration.jei.JeiCloth;
import com.jab125.clothintegration.platform.PlatformUtil;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("clothintegration")
public class ForgeMain {

    public ForgeMain() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TestConfig.CLIENT_SPEC);
        bus.addListener(this::loaded);
    }

    private void loaded(FMLLoadCompleteEvent event) {
        event.enqueueWork(ConfigIntegration::init);
    }
}
//#endif