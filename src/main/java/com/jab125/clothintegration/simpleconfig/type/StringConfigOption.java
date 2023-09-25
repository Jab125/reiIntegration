package com.jab125.clothintegration.simpleconfig.type;

import com.google.common.reflect.TypeToken;
import com.jab125.clothintegration.util.Lazy;

public class StringConfigOption extends ConfigOption<String> {
    protected StringConfigOption(String defaultValue) {
        super(defaultValue);
    }

    protected StringConfigOption(Lazy<String> defaultValue) {
        super(defaultValue);
    }

    @Override
    public String serialize(String value) {
        return value;
    }

    @Override
    public String deserialize(String t) {
        return t;
    }

    @Override
    public Type<String> getType() {
        return Type.create(new TypeToken<>() {});
    }
}
