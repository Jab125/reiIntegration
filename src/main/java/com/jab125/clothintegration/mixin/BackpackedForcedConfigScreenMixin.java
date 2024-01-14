package com.jab125.clothintegration.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "com.mrcrayfish.backpacked.platform.FabricScreenHelper", remap = false)
public class BackpackedForcedConfigScreenMixin {

	@Inject(method = "openConfigScreen()V", at = @At("HEAD"), cancellable = true, require = 0)
	void openConfigScreen(CallbackInfo callbackInfo) {
		//#if LOADER == FABRIC
		MinecraftClient.getInstance().setScreen(com.terraformersmc.modmenu.ModMenu.getConfigScreen("backpacked", MinecraftClient.getInstance().currentScreen));
		callbackInfo.cancel();
		//#endif
	}
}
