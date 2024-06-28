//#if LOADER<=FORGE
//$$ package com.jab125.clothintegration;
//$$
//$$ import com.jab125.clothintegration.platform.PlatformUtil;
//$$ import net.minecraftforge.api.distmarker.Dist;
//$$ import net.minecraftforge.fml.common.Mod;
//$$ import net.minecraftforge.fml.loading.FMLEnvironment;
//$$ import java.util.function.Supplier;
//$$
//$$ @Mod("roughlyenoughconfigscreens")
//$$ public class ForgeMain {
//$$
//$$     public ForgeMain() {
        //#if LOADER==NEO
        //$$ PlatformUtil.assertNeoForge();
        //#endif
//$$         Supplier<Supplier<Runnable>> supplier = () -> () -> ConfigIntegration::init;
//$$         if (FMLEnvironment.dist == Dist.CLIENT) {
//$$             supplier.get().get().run();
//$$         }
//$$     }
//$$ }
//#endif