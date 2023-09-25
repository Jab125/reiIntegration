package com.jab125.clothintegration.mixin;

import me.shedaniel.clothconfig2.gui.AbstractConfigScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractConfigScreen.class)
public interface AbstractConfigScreenAccessor {
    @Mutable
    @Accessor
    void setParent(Screen parent);
}
