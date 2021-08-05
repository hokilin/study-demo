package cn.hokilin.studydemo.random;

import org.springframework.util.StopWatch;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author linhuankai
 * @date 2021/7/22 11:55
 */
public class RandomUtils {


    /**
     * 随机指定范围内n个不重复的数
     * 当 n小于等于指定范围总个数的1/4时，即n <= (max - min + 1)/4，尽量追求生成的随机数不存在有相邻的情况
     *
     * @param min 指定范围最小值(包括)
     * @param max 指定范围最大值(包括)
     * @param n   随机数个数
     * @return
     */
    public static Set<Integer> randomSet(int min, int max, int n) {
        if (n > (max - min + 1) || max < min) {
            return Collections.emptySet();
        }

        Set<Integer> set = new HashSet<>(n);
        // 随机数相邻控制
        boolean adjoinControl = n <= (max - min + 1) / 4;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // 最大循环次数控制
        int maxForEachTimes = n * 1000;
        if (adjoinControl) {
            int forEachTimes = 0;
            do {
                int nextInt = random.nextInt(min, max + 1);
                // 相邻控制
                if (!set.contains(nextInt - 1) && !set.contains(nextInt + 1)) {
                    set.add(nextInt);
                }
                forEachTimes++;
            } while (set.size() < n && forEachTimes < maxForEachTimes);
        } else {
            IntStream intStream = random.ints(n, min, max + 1);
            set = intStream.boxed().collect(Collectors.toSet());
            // 去重后size不够继续添加
            int forEachTimes = 0;
            while (set.size() < n && forEachTimes < maxForEachTimes) {
                int nextInt = random.nextInt(min, max + 1);
                set.add(nextInt);
                forEachTimes++;
            }
        }
        return set;
    }

    public static void main(String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        IntStream intStream = random.ints(10, 100, 110);
        Set<Integer> integerList = intStream.boxed().collect(Collectors.toSet());
        Set<Integer> integerSet = integerList.stream().filter(value -> !integerList.contains(value - 1) && !integerList.contains(value + 1)).collect(Collectors.toSet());
        System.out.println(integerList);
        System.out.println(integerSet);
        System.out.println("--------------------");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Set<Integer> randomSet = randomSet(1, 1000, 10);
        stopWatch.stop();
        System.out.println("TotalTimeNanos: " + stopWatch.getTotalTimeNanos());
        System.out.println("TotalTimeMillis: " + stopWatch.getTotalTimeMillis());
        List<Integer> sortList = randomSet.stream().sorted().collect(Collectors.toList());
        System.out.println(sortList);
        System.out.println(sortList.size());

        System.out.println(1500%100);
        System.out.println(1599%100);
    }
}
