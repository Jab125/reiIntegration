package com.jab125.clothintegration.simpleconfig.type;

import com.google.common.reflect.TypeToken;
import com.jab125.clothintegration.util.Lazy;

public class IntConfigOption extends ConfigOption<Integer> {
    protected IntConfigOption(Integer defaultValue) {
        super(defaultValue);
    }

    protected IntConfigOption(Lazy<Integer> defaultValue) {
        super(defaultValue);
    }

    @Override
    public String serialize(Integer value) {
        return value.toString();
    }

    @Override
    public Integer deserialize(String t) {
        return Integer.parseInt(t);
    }

    @Override
    public Type<Integer> getType() {
        return Type.create(new TypeToken<>() {});
    }

    @Override
    public boolean isValid(String t) {
        try {
            Integer.parseInt(t);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
