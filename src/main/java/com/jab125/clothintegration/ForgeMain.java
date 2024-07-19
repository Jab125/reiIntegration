//#if LOADER<=FORGE
package com.jab125.clothintegration;

import com.jab125.clothintegration.platform.PlatformUtil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import java.util.function.Supplier;
@Mod("roughlyenoughconfigscreens")
public class ForgeMain {

	//#if LOADER == NEO && MC >= 1.21
//$$ 	public ForgeMain(IEventBus bus) {
	//#else
	public ForgeMain() {
		IEventBus bus = net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus();
	//#endif
		//#if LOADER==NEO
//$$ 		PlatformUtil.assertNeoForge();
		//#endif
		Supplier<Supplier<Runnable>> runnable = () -> () -> () -> {
			bus.addListener(this::loaded);
			// ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TestConfig.CLIENT_SPEC);
		};
		if (FMLEnvironment.dist == Dist.CLIENT) runnable.get().get().run();
	}

	private void loaded(FMLLoadCompleteEvent event) {
		Supplier<Supplier<Runnable>> runnable = () -> () -> () -> event.enqueueWork(ConfigIntegration::init);
		if (FMLEnvironment.dist == Dist.CLIENT) runnable.get().get().run();
	}
}
//#endif