package com.jab125.clothintegration.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "com.mrcrayfish.backpacked.platform.FabricScreenHelper")
public class BackpackedForcedConfigScreenMixin {

	@Inject(method = "openConfigScreen()V", at = @At("HEAD"), cancellable = true)
	void openConfigScreen(CallbackInfo callbackInfo) {
		callbackInfo.cancel();
	}
}
