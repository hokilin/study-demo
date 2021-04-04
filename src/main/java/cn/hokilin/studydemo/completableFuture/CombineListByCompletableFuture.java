package cn.hokilin.studydemo.completableFuture;

import com.google.common.collect.Lists;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author linhuankai
 * @date 2021/4/3 21:52
 */
public class CombineListByCompletableFuture {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    public static List<Integer> getList() {
        List<Integer> list = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        return list;
    }

    public static List<Integer> getListForATime(List<Integer> param) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param;
    }

    public static void test1() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("getInitList");
        List<Integer> initList = getList();
        stopWatch.stop();
        stopWatch.start("partition");
        List<List<Integer>> partition = Lists.partition(initList, 1000);
        stopWatch.stop();
        stopWatch.start("main task");
        List<CompletableFuture<List<Integer>>> futureList = new ArrayList<>(partition.size());
        for (List<Integer> list : partition) {
            CompletableFuture<List<Integer>> completableFuture = CompletableFuture.supplyAsync(() -> getListForATime(list));
            futureList.add(completableFuture);
        }
        //聚合
        CompletableFuture<List<List<Integer>>> joinFutureList = CompletableFuture.allOf(futureList.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> futureList.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        List<Integer> resultList = joinFutureList.thenApply(lists -> lists.stream().flatMap(Collection::stream).collect(Collectors.toList())).join();
        stopWatch.stop();
        System.out.println(resultList);
        System.out.println(resultList.size());
        System.out.println(stopWatch.prettyPrint());
    }

    public static void test2() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("getInitList");
        List<Integer> initList = getList();
        stopWatch.stop();
        stopWatch.start("partition");
        List<List<Integer>> partition = Lists.partition(initList, 1000);
        stopWatch.stop();
        stopWatch.start("main task");
        List<Integer> resultList = Collections.synchronizedList(new ArrayList<>(100000));
        List<CompletableFuture<Void>> futureList = new ArrayList<>(partition.size());
        for (List<Integer> list : partition) {
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> getListForATime(list)).thenAccept(resultList::addAll);
            futureList.add(completableFuture);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture<?>[0])).join();
        stopWatch.stop();
        System.out.println(resultList);
        System.out.println(resultList.size());
        System.out.println(stopWatch.prettyPrint());
    }

    public static void test3() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("getInitList");
        List<Integer> initList = getList();
        stopWatch.stop();
        stopWatch.start("partition");
        List<List<Integer>> partition = Lists.partition(initList, 1000);
        stopWatch.stop();
        stopWatch.start("main task");
        List<CompletableFuture<List<Integer>>> futureList = new ArrayList<>(partition.size());
        for (List<Integer> list : partition) {
            CompletableFuture<List<Integer>> completableFuture = CompletableFuture.supplyAsync(() -> getListForATime(list));
            futureList.add(completableFuture);
        }
        List<Integer> resultList = futureList.stream()
                .reduce((fn1, fn2) -> fn1.thenCombine(fn2, (integers, integers2) -> Stream.of(integers, integers2).flatMap(Collection::stream).collect(Collectors.toList())))
                .orElse(CompletableFuture.completedFuture(Collections.emptyList()))
                .join();
        stopWatch.stop();
        System.out.println(resultList);
        System.out.println(resultList.size());
        System.out.println(stopWatch.prettyPrint());
    }
}
