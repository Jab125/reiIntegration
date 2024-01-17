package com.jab125.clothintegration.mixin;

import com.jab125.clothintegration.util.ModConfig;
import me.shedaniel.clothconfig2.ClothConfigInitializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ClothConfigInitializer.class, remap = false)
public class ClothConfigInitializerMixin {
	@Inject(method = "getScrollDuration", at = @At("HEAD"), cancellable = true)
	private static void getScrollDuration(CallbackInfoReturnable<Long> cir) {
		if (!ModConfig.$().smoothScrolling) cir.setReturnValue(0L);
	}
}
