//#if LOADER<=FORGE
//$$ package com.jab125.clothintegration;
//$$
//$$ import com.jab125.clothintegration.forge.test.TestConfig;
//$$ import com.jab125.clothintegration.platform.PlatformUtil;
//$$ import net.minecraftforge.api.distmarker.Dist;
//$$ import net.minecraftforge.client.ConfigScreenHandler;
//$$ import net.minecraftforge.eventbus.api.IEventBus;
//$$ import net.minecraftforge.fml.DistExecutor;
//$$ import net.minecraftforge.fml.ModList;
//$$ import net.minecraftforge.fml.ModLoadingContext;
//$$ import net.minecraftforge.fml.common.Mod;
//$$ import net.minecraftforge.fml.config.ModConfig;
//$$ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//$$ import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
//$$ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//$$
//$$ @Mod("roughlyenoughconfigscreens")
//$$ public class ForgeMain {
//$$
//$$     public ForgeMain() {
        //#if LOADER==NEO
        //$$ PlatformUtil.assertNeoForge();
        //#endif
//$$         DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> {
//$$             IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
//$$             // ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TestConfig.CLIENT_SPEC);
//$$             bus.addListener(this::loaded);
//$$         });
//$$     }
//$$
//$$     private void loaded(FMLLoadCompleteEvent event) {
//$$         DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> event.enqueueWork(ConfigIntegration::init));
//$$     }
//$$ }
//#endif