package com.jab125.clothintegration.simpleconfig.type;

import com.google.common.reflect.TypeToken;
import com.jab125.clothintegration.util.Lazy;

import java.util.function.Supplier;

public class EnumConfigOption<T extends Enum<T>> extends ConfigOption<T> {

    private final TypeToken<T> typeToken;
    private final Lazy<Class<T>> rawType;

    protected EnumConfigOption(T defaultValue, TypeToken<T> typeToken) {
        super(defaultValue);
        this.typeToken = typeToken;
        this.rawType = new Lazy<>(() -> (Class<T>) typeToken.getRawType());
    }

    protected EnumConfigOption(Lazy<T> defaultValue, TypeToken<T> typeToken) {
        super(defaultValue);
        this.typeToken = typeToken;
        this.rawType = new Lazy<>(() -> (Class<T>) typeToken.getRawType());
    }

    @Override
    public String serialize(T value) {
        return value.name();
    }

    @Override
    public T deserialize(String t) {
        return Enum.valueOf(rawType.get(), t);
    }

    @Override
    public Type<T> getType() {
        return Type.create(typeToken);
    }

    @Override
    public boolean isValid(String t) {
        try {
            deserialize(t);
        } catch (Throwable e) {
            return false;
        }
        return true;
    }
}
