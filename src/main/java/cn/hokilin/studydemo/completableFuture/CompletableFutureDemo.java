package cn.hokilin.studydemo.completableFuture;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

/**
 * @author linhuankai
 * @date 2020/12/29 21:41
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("step1");
            return 999;
        });
        future.thenAccept((result) -> System.out.println("result" + result));
        boolean complete = future.complete(500);
        System.out.println(complete);
        future.exceptionally((error) -> {
            error.printStackTrace();
            return null;
        });
        Thread.sleep(5000);
    }
}
