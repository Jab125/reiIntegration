package com.jab125.clothintegration.mixin;

import mezz.jei.common.config.file.serializers.IntegerSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(IntegerSerializer.class)
public interface IntegerSerializerAccessor {
    @Accessor
    int getMin();

    @Accessor
    int getMax();
}
