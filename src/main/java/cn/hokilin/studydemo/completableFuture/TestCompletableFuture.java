package cn.hokilin.studydemo.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * @author linhuankai
 * @date 2020/12/29 21:49
 */
public class TestCompletableFuture {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture.supplyAsync
                (new MyThread1(), executorService)
                .whenComplete((result, e) -> {
                    //执行线程执行完以后的操作。
                    System.out.println(result + " " + e);
                }).exceptionally((e) -> {
            //抛出异常
            System.out.println("exception " + e);
            return "exception";
        });

        CompletableFuture.supplyAsync
                (new MyThread2(), executorService)
                .whenComplete((result, e) -> {
                    //执行线程执行完以后的操作。
                    System.out.println(result + " " + e);
                }).exceptionally((e) -> {
            System.out.println("exception " + e);
            return "exception";
        });
    }
}

class MyThread1 implements Supplier<String> {

    @Override
    public String get() {
        int sum = 0;
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sum += i;
            System.out.println("MyThread1: " + i);
        }
        return String.valueOf(sum + 300000);
    }
}

class MyThread2 implements Supplier<String> {

    @Override
    public String get() {
        int sum = 0;
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sum += i;
            System.out.println("MyThread2: " + i);
        }
        return String.valueOf(sum + 400000);
    }
}
