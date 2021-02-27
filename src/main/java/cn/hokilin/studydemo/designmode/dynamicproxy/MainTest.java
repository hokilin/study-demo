package cn.hokilin.studydemo.designmode.dynamicproxy;

import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import java.lang.reflect.Method;

/**
 * @author linhuankai
 * @date 2021/2/1 22:18
 */
public class MainTest {
    public static void main(String[] args) throws Exception {

        BookApi delegate = new BookApiImpl();
        long time = System.currentTimeMillis();
        BookApi jdkProxy = JdkDynamicProxy.createJdkDynamicProxy(delegate);
        time = System.currentTimeMillis() - time;
        System.out.println("Create JDK Proxy: " + time + " ms");

        time = System.currentTimeMillis();
        BookApi cglibProxy = CglibDynamicProxy.createCglibDynamicProxy(delegate);
        time = System.currentTimeMillis() - time;
        System.out.println("Create CGLIB Proxy: " + time + " ms");

        time = System.currentTimeMillis();
        BookApi javassistBytecodeProxy = JavassistDynamicProxy.createJavassistBytecodeDynamicProxy();
        time = System.currentTimeMillis() - time;
        System.out.println("Create JavassistBytecode Proxy: " + time + " ms");

        for (int i = 0; i < 10; i++) {
            //warm
            jdkProxy.sell();
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            jdkProxy.sell();
        }
        System.out.println("JDK Proxy invoke cost " + (System.currentTimeMillis() - start) + " ms");

        for (int i = 0; i < 10; i++) {
            //warm
            cglibProxy.sell();
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            cglibProxy.sell();
        }
        System.out.println("CGLIB Proxy invoke cost " + (System.currentTimeMillis() - start) + " ms");

        for (int i = 0; i < 10; i++) {
            //warm
            javassistBytecodeProxy.sell();
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            javassistBytecodeProxy.sell();
        }
        System.out.println("JavassistBytecode Proxy invoke cost " + (System.currentTimeMillis() - start) + " ms");

        Class<?> serviceClass = delegate.getClass();
        String methodName = "sell";
        for (int i = 0; i < 10; i++) {
            cglibProxy.sell();//warm
        }
        // 执行反射调用
        //warm
        for (int i = 0; i < 10; i++) {
            Method method = serviceClass.getMethod(methodName);
            method.invoke(delegate);
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Method method = serviceClass.getMethod(methodName);
            method.invoke(delegate);
        }
        System.out.println("反射 invoke cost " + (System.currentTimeMillis() - start) + " ms");

        // 使用 CGLib 执行反射调用
        //warm
        for (int i = 0; i < 10; i++) {
            FastClass serviceFastClass = FastClass.create(serviceClass);
            FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, new Class[]{});
            serviceFastMethod.invoke(delegate, new Object[]{});
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            FastClass serviceFastClass = FastClass.create(serviceClass);
            FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, new Class[]{});
            serviceFastMethod.invoke(delegate, new Object[]{});
        }
        System.out.println("CGLIB invoke cost " + (System.currentTimeMillis() - start) + " ms");

    }
}
