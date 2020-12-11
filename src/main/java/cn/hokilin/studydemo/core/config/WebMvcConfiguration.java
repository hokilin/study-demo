package cn.hokilin.studydemo.core.config;

import cn.hokilin.studydemo.core.interceptors.DynaFiledInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author linhuankai
 * @date 2020/9/8 19:12
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private DynaFiledInterceptor dynaFiledInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
//        registry.addInterceptor(dynaFiledInterceptor).addPathPatterns("/dyna/**");
    }
}
