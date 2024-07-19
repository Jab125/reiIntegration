package com.jab125.clothintegration.mixin;

import me.shedaniel.clothconfig2.impl.builders.FieldBuilder;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Optional;
import java.util.function.Function;

@Mixin(FieldBuilder.class)
public interface FieldBuilderAccessor<T> {
    @Accessor
    Function<T, Optional<Text>> getErrorSupplier();
}
