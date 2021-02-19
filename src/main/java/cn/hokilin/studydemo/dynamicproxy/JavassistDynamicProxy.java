package cn.hokilin.studydemo.dynamicproxy;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;

/**
 * @author linhuankai
 * @date 2021/2/1 0:38
 */
public class JavassistDynamicProxy {
    static BookApi createJavassistBytecodeDynamicProxy() throws Exception {
        ClassPool mPool = new ClassPool(true);
        CtClass mCtc = mPool.makeClass(BookApi.class.getName() + "JavaassistProxy");
        mCtc.addInterface(mPool.get(BookApi.class.getName()));
        mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
        mCtc.addMethod(CtNewMethod.make(
                "public void sell() { System.out.print(\"\") ; }", mCtc));
        Class<?> pc = mCtc.toClass();
        return (BookApi) pc.newInstance();
    }
}
