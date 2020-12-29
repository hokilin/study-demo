package cn.hokilin.studydemo.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author linhuankai
 * @date 2020/12/8 0:33
 */
public class MyCompletableFuture1 {
    public static void main(String[] args) throws Exception {
        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> queryCode("中国石油"));
        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync(MyCompletableFuture1::fetchPrice);
        // cfFetch成功后打印结果:
        cfFetch.thenAccept((result) -> System.out.println("price: " + result));
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    private static String queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    private static Double fetchPrice(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}
