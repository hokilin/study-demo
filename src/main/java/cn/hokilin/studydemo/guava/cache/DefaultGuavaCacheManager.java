package cn.hokilin.studydemo.guava.cache;

import lombok.extern.slf4j.Slf4j;

/**
 * @author linhuankai
 * @date 2021/8/2 18:39
 */
@Slf4j
public class DefaultGuavaCacheManager {

    /**
     * 缓存包装类
     */
    private static AbstractGuavaCache<String, Object> cacheWrapper;

    /**
     * 初始化缓存容器
     */
    public static boolean initGuavaCache() {
        try {
            cacheWrapper = DefaultGuavaCache.getInstance();
            if (cacheWrapper != null) {
                return true;
            }
        } catch (Exception e) {
            log.error("Failed to init guava cache", e);
        }
        return false;
    }

    public static void put(String key, Object value) {
        cacheWrapper.getCache().put(key, value);
    }

    /**
     * 指定缓存失效
     *
     * @param key
     */
    public static void invalidate(String key) {
        cacheWrapper.getCache().invalidate(key);
    }

    /**
     * 批量清除
     *
     * @param keys
     */
    public static void invalidateAll(Iterable<?> keys) {
        cacheWrapper.getCache().invalidateAll(keys);
    }

    /**
     * 清除所有缓存项 ： 慎用
     */
    public static void invalidateAll() {
        cacheWrapper.getCache().invalidateAll();
    }

    public static Object get(String key) {
        try {
            return cacheWrapper.getCache().get(key);
        } catch (Exception e) {
            log.error("Failed to get value from guava cache", e);
        }
        return null;
    }

    /**
     * 使用静态内部类实现一个默认的缓存，委托给manager来管理
     * DefaultGuavaCache 使用一个简单的单例模式
     */
    private static class DefaultGuavaCache<K, V> extends
            AbstractGuavaCache<K, V> {

        private static final AbstractGuavaCache<String, Object> CACHE = new DefaultGuavaCache<>();

        /**
         * 处理自动载入缓存，按实际情况载入
         *
         * @param key
         * @return
         */
        @Override
        protected V fetchData(K key) {
            return null;
        }

        public static AbstractGuavaCache<String, Object> getInstance() {
            return DefaultGuavaCache.CACHE;
        }

    }

}