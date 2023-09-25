package com.jab125.clothintegration.simpleconfig.type;

import com.google.common.hash.Hashing;
import com.google.common.reflect.TypeToken;
import com.jab125.clothintegration.util.Lazy;

import java.nio.charset.StandardCharsets;

public abstract class ConfigOption<T> {

    private final Lazy<T> defaultValue;
    protected T currentValue;

    protected ConfigOption(T defaultValue) {
        this.defaultValue = Lazy.fulfilled(defaultValue);
    }

    protected ConfigOption(Lazy<T> defaultValue) {
        this.defaultValue = defaultValue;
    }

    public T getDefaultValue() {
        return defaultValue.get();
    }

    public T getValue() {
        return currentValue;
    }

    public void setValue(T val) {
        currentValue = val;
    }

    public String serialize() {
        return serialize(currentValue);
    }

    public abstract String serialize(T value);

    public abstract T deserialize(String t);

    public abstract Type<T> getType();

    public boolean isValid(String t) {
        return true;
    }

    public static class Type<T> {

        private final TypeToken<T> token;

        private Type(TypeToken<T> token) {
            this.token = token;
        }

        public static <T> Type<T> create(TypeToken<T> typeToken) {
            return new Type<>(typeToken);
        }

        public String getId() {
            return Hashing.murmur3_128().hashString(token.toString(), StandardCharsets.UTF_8).toString();
        }
    }
}
