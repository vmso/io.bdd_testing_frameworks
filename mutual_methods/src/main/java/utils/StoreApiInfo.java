package utils;

import java.util.concurrent.ConcurrentHashMap;

public class StoreApiInfo {

    private static final ThreadLocal<ConcurrentHashMap<String, Object>> map = ThreadLocal.withInitial(ConcurrentHashMap::new);

    private StoreApiInfo() {
        throw new IllegalStateException("Utility class");
    }

    public static synchronized void put(String key, Object value) {

        if (key != null && value != null) {
            map.get().put(key, value);
        }

    }

    public static synchronized Object remove(Object key) {
        return key != null ? map.get().remove(key) : null;
    }

    public static synchronized void remove() {
        map.remove();
    }

    public static synchronized Object get(Object key) {
        return key != null ? map.get().get(key) : null;
    }

    public static synchronized void clear() {
        map.get().clear();
    }
}
