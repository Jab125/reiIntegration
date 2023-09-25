package com.jab125.clothintegration.simpleconfig.type;

import com.google.common.reflect.TypeToken;
import com.jab125.clothintegration.util.Lazy;

public class BooleanConfigOption extends ConfigOption<Boolean> {
    protected BooleanConfigOption(boolean defaultValue) {
        super(defaultValue);
    }

    protected BooleanConfigOption(Lazy<Boolean> defaultValue) {
        super(defaultValue);
    }

    @Override
    public String serialize(Boolean value) {
        return value.toString();
    }

    @Override
    public Boolean deserialize(String t) {
        return Boolean.parseBoolean(t);
    }

    @Override
    public Type<Boolean> getType() {
        return Type.create(new TypeToken<>(){});
    }

    @Override
    public boolean isValid(String t) {
        return "true".equals(t) || "false".equals(t);
    }
}
