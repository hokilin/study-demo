package cn.hokilin.studydemo.utils;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Lazy(false)
@Component
public final class SpringUtils implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext;

    private SpringUtils() {
    }

    /**
     * 获取applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取实例
     *
     * @param name bean名称
     * @return 实例
     */
    public static Object getBean(String name) {
        Assert.hasText(name, "Bean名称不能为空");
        return applicationContext.getBean(name);
    }

    /**
     * 获取bean实例
     *
     * @param type bean类型
     * @param <T>  类型
     * @return 实例
     */
    public static <T> T getBean(Class<T> type) {
        Assert.notNull(type, "Bean类型不能为空");
        return applicationContext.getBean(type);
    }

    /**
     * 获取实例
     *
     * @param name 名称
     * @param type 类型
     * @param <T>  类型
     * @return 实例
     */
    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name, "Bean名称不能为空");
        Assert.notNull(type, "Bean类型不能为空");
        return applicationContext.getBean(name, type);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtils.applicationContext = applicationContext;
    }

    @Override
    public void destroy() {
        SpringUtils.applicationContext = null;
    }
}
