package cn.hokilin.studydemo.reference;

import lombok.SneakyThrows;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author linhuankai
 * @date 2020/12/19 10:16
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
//        test1();
        test2();
    }
    
    private static void test1() {
        //初始化，声明强引用
        TestNormalObject testNormalObject = new TestNormalObject();
        //声明软引用
        SoftReference<TestNormalObject> testNormalObjectSoftReference = new SoftReference<>(testNormalObject);
        //初始化，声明强引用
        TestFinalizeObject testFinalizeObject = new TestFinalizeObject();
        //声明软引用
        SoftReference<TestFinalizeObject> testFinalizeObjectSoftReference = new SoftReference<>(testFinalizeObject);

        //强引用去掉。让对象只剩下软引用
        testNormalObject = null;
        testFinalizeObject = null;

        //看是否还存在
        System.out.println(testNormalObjectSoftReference.get());
        System.out.println(testFinalizeObjectSoftReference.get());

        try {
            //我们启动参数设置的是-Xmx16m，所以这里一定会抛出内存溢出的错误
            //也一定会触发回收软引用
            byte[] test = new byte[1024 * 1024 * 16];
        } catch (Error e) {
            System.out.println(e.getClass().getName() + ":" + e.getMessage());
        }

        //看是否还存在
        System.out.println(testNormalObjectSoftReference.get());
        System.out.println(testFinalizeObjectSoftReference.get());
    }
    
    @SneakyThrows
    private static void test2() {
        ReferenceQueue<SoftReferenceDemo.TestNormalObject> testNormalObjectReferenceQueue = new ReferenceQueue<>();
        ReferenceQueue<SoftReferenceDemo.TestFinalizeObject> testFinalizeObjectReferenceQueue = new ReferenceQueue<>();

        //初始化，声明强引用
        SoftReferenceDemo.TestNormalObject testNormalObject = new SoftReferenceDemo.TestNormalObject();
        //声明软引用
        SoftReference<SoftReferenceDemo.TestNormalObject> testNormalObjectSoftReference = new SoftReference<>(testNormalObject, testNormalObjectReferenceQueue);
        //初始化，声明强引用
        SoftReferenceDemo.TestFinalizeObject testFinalizeObject = new SoftReferenceDemo.TestFinalizeObject();
        //声明软引用
        SoftReference<SoftReferenceDemo.TestFinalizeObject> testFinalizeObjectSoftReference = new SoftReference<>(testFinalizeObject, testFinalizeObjectReferenceQueue);

        System.out.println("Origin: " + testNormalObjectSoftReference);
        System.out.println("Origin: " + testFinalizeObjectSoftReference);


        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("GOT From Queue: " + testNormalObjectReferenceQueue.remove().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("GOT From Queue: " + testFinalizeObjectReferenceQueue.remove().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();

        //强引用去掉。让对象只剩下软引用
        testNormalObject = null;
        testFinalizeObject = null;

        //看是否还存在
        System.out.println(testNormalObjectSoftReference.get());
        System.out.println(testFinalizeObjectSoftReference.get());

        try {
            //我们启动参数设置的是-Xmx16m，所以这里一定会抛出内存溢出的错误
            //也一定会触发回收软引用
            byte[] test = new byte[1024 * 1024 * 16];
        } catch (Error e) {
            System.out.println(e.getClass().getName() + ":" + e.getMessage());
        }

        //看是否还存在
        System.out.println(testNormalObjectSoftReference.get());
        System.out.println(testFinalizeObjectSoftReference.get());

        thread1.join();
        thread2.join();
    }

    /**
     * 第一种类。未覆盖finalize
     */
    public static class TestNormalObject {
    }

    /**
     * 第二种类。覆盖finalize的类
     */
    public static class TestFinalizeObject {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalize is called");
        }
    }
}
