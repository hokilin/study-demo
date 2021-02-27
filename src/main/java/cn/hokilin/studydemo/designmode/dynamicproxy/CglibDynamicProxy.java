package cn.hokilin.studydemo.designmode.dynamicproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author linhuankai
 * @date 2021/2/1 0:29
 */
public class CglibDynamicProxy {
    static BookApi createCglibDynamicProxy(final BookApi delegate) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibInterceptor(delegate));
        enhancer.setInterfaces(new Class[]{BookApi.class});
        return (BookApi) enhancer.create();
    }

    private static class CglibInterceptor implements MethodInterceptor {

        final Object delegate;

        CglibInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object intercept(Object object, Method method, Object[] objects,
                                MethodProxy methodProxy) throws Throwable {
            //添加代理逻辑
            if ("sell".equals(method.getName())) {
                System.out.print("");
            }
//            return null;
            return methodProxy.invoke(delegate, objects);
        }
    }
}
