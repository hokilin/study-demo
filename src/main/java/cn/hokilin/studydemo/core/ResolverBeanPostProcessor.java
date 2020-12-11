package cn.hokilin.studydemo.core;

import cn.hokilin.studydemo.dynamicfield.DynaFiledResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linhuankai
 * @date 2020/12/11 16:18
 */
public class ResolverBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private DynaFiledResolver dynaFiledResolver;

    private static final String BEAN_NAME = "ResolverBeanPostProcessor";

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (BEAN_NAME.equals(beanName)) {
            //requestMappingHandlerAdapter进行修改
            RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;
            List<HandlerMethodArgumentResolver> argumentResolvers = adapter.getArgumentResolvers();

            //添加自定义参数处理器
            argumentResolvers = addArgumentResolvers(argumentResolvers);

            adapter.setArgumentResolvers(argumentResolvers);
        }
        return bean;
    }

    private List<HandlerMethodArgumentResolver> addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>();

        //将自定的添加到最前面
        resolvers.add(dynaFiledResolver);
        //将本来的添加后面
        resolvers.addAll(argumentResolvers);
        return resolvers;
    }
}
