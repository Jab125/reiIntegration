package com.jab125.clothintegration.util;

import java.util.function.Supplier;

public class Lazy<T> {
    private static final Supplier<?> EMPTY = () -> null;

    private Supplier<T> supplier;
    private T value;

    public Lazy(Supplier<T> tSupplier) {
        if (tSupplier == null) throw new IllegalArgumentException();
        supplier = tSupplier;
    }

    public static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }

    public static <T> Lazy<T> fulfilled(T value) {
        // noinspection unchecked
        Lazy<T> lazy = new Lazy<>((Supplier<T>) EMPTY);
        lazy.supplier = null;
        lazy.value = value;
        return lazy;
    }

    public T get() {
        if (supplier != null) { value = supplier.get(); supplier = null; }
        return value;
    }
}
