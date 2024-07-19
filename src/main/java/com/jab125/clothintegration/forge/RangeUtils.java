package com.jab125.clothintegration.forge;

import net.minecraft.text.Text;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Predicate;

public class RangeUtils {
    private static final Class<?> clazz;
    private static final Method getMin;
    private static final Method getMax;
    private static final Method getClazz;
    static {
        try {
            clazz = Class.forName("net.minecraftforge.common.ForgeConfigSpec$Range");
            getMin = clazz.getDeclaredMethod("getMin");
            getMin.setAccessible(true);
            getMax = clazz.getDeclaredMethod("getMax");
            getMax.setAccessible(true);
            getClazz = clazz.getDeclaredMethod("getClazz");
            getClazz.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public interface UnsafeSupplier<T, V> {
        T get(V v) throws Throwable;
    }

    public static <T, V> T callUnsafely(UnsafeSupplier<V, Void> unsafeSupplier) {
        try {
            return (T) unsafeSupplier.get(null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    public static <V extends Comparable<? super V>> RangeWrapper<V> wrapRange(Object object) {
        return new RangeWrapper<>(object);
    }

    public static class RangeWrapper<V extends Comparable<? super V>> implements Predicate<Object> {
        private static final String TOO_LARGE = "text.clothintegration.error.toolarge";
        private static final String TOO_SMALL = "text.clothintegration.error.toosmall";
        private static final String UNKNOWN = "text.clothintegration.error.unknown";
        final Predicate<Object> range;
        // this is for internal purposes
        public String key;
        public RangeWrapper(Object range) {
            this.range = (Predicate<Object>) range;
        }

        public Class<? extends V> getClazz() {
            return callUnsafely(c -> getClazz.invoke(range));
        }

        public V getMin() {
            return callUnsafely(c -> getMin.invoke(range));
        }

        public V getMax() {
            return callUnsafely(c -> getMax.invoke(range));
        }

        public boolean isAvailable(Class<?> clazz) {
            if (clazz == null) return false;
            if (range == null) return false;
            if (!clazz.isAssignableFrom(getClazz())) return false;
            return true;
        }

        public boolean isUsable(Class<?> clazz) {
            if (!isAvailable(clazz)) return false;
            if (getMin() == null) return false;
            if (getMax() == null) return false;
            return true;
        }

        @Override
        public boolean test(Object object) {
            return range.test(object);
        }

        // let's assume it's comparable
        public <T extends Comparable<T>> Optional<Text> errorSupplier(T t) {
            V val = (V) t;
            if (val.compareTo(getMin()) < 0) return Optional.of(Text.translatable(TOO_SMALL, key, val.toString()));
            if (val.compareTo(getMax()) > 0) return Optional.of(Text.translatable(TOO_LARGE, key, val.toString()));
            if (!test(val)) return Optional.of(Text.translatable(UNKNOWN, key, val.toString()));
            return Optional.empty();
        }
    }
}
