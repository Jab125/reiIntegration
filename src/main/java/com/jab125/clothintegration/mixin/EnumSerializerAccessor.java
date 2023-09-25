package com.jab125.clothintegration.mixin;

import mezz.jei.common.config.file.serializers.EnumSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EnumSerializer.class)
public interface EnumSerializerAccessor {
    @Accessor
    Class<?> getEnumClass();
}
