package cn.hokilin.studydemo.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author linhuankai
 * @date 2021/2/1 0:21
 */
public class JdkDynamicProxy {
    static BookApi createJdkDynamicProxy(final BookApi delegate) {
        return (BookApi) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{BookApi.class}, new JdkHandler(delegate));
    }

    private static class JdkHandler implements InvocationHandler {

        final Object delegate;

        JdkHandler(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object object, Method method, Object[] objects)
                throws Throwable {
            // 添加代理逻辑
            // 在真正的 RPC 调用中 ，需要填充‘整理报文’，‘确认网络位置’，‘序列化’,’网络传输’，‘反序列化’，’返回结果’等逻辑。
            if ("sell".equals(method.getName())) {
                System.out.print("");
            }
//            return null;
            return method.invoke(delegate, objects);
        }
    }
}
