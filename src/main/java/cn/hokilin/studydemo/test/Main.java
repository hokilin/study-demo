package cn.hokilin.studydemo.test;

/**
 * @author linhuankai
 * @date 2021/3/18 21:47
 */

import cn.hokilin.studydemo.utils.IdGeneratorNew;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//
//        List<Long> a = Arrays.asList(3309794746L, 3308827999L, 3309794728L, 3801210995L, 3309794727L, 3309260673L, 3309260664L, 3309260638L, 3802588051L, 3308828029L, 3308827951L, 3308827862L, 3308525785L, 3308525750L, 3308525701L, 3308525684L, 3308525682L, 3801247207L, 3801349319L, 3308525578L, 3308525527L, 3308525415L, 3308285297L, 3801209421L, 3308285163L, 3308285043L, 3308285018L, 3802425636L, 3307858145L, 3801209409L, 3801209404L, 3801229731L, 3308058477L, 17810690L, 3307858207L, 3307858202L, 3307858174L, 3305884764L, 3307858147L, 3307858133L, 3307858125L, 3307858046L, 3307858000L, 3307858003L, 3307857915L, 3307230482L, 3801171117L, 3801171105L, 3801536734L, 3307685315L, 3307685307L, 3307685305L, 3305743133L, 3307406892L, 3307406850L, 3307406851L, 3801211003L, 3801330863L, 3801392946L, 3802581996L, 3306999156L, 3801247135L, 3801184269L, 3306767587L, 3801740511L, 3801464001L, 3801349317L, 3801330851L, 3801210998L, 3801171112L, 3306893758L, 3802582025L, 3306677740L, 3801330925L, 3305757219L, 3802425687L, 3306770673L, 3306767590L, 3306767591L, 3306770602L, 3305743175L, 3306767584L, 3306755361L, 3306755360L, 3306732822L, 3306677792L, 3305605414L, 3801612228L, 3801171115L, 3801183436L, 3304010186L, 3801183551L, 3801448874L, 17801438L, 17800074L, 3801184233L, 3304009015L, 3802545174L, 3801463984L, 3801197191L, 17810682L, 3801210989L, 3801197190L, 3801197195L, 3801211004L, 3801247167L, 3801330889L, 3801340094L, 3801247208L, 3304010202L, 3801184273L, 3801184037L, 3801829972L, 3801829971L, 3801794915L, 3801211019L, 3801229693L, 17810774L, 3801210604L, 3801225465L, 17810758L, 3801171096L, 3801829973L, 3801597123L, 3801247159L, 3801229665L, 3802547311L, 3801247169L, 3801247172L, 3801247168L, 3801330869L, 3801842202L, 3801829974L, 3801247162L, 3801247149L, 3801247166L, 3801247163L);
//        List<Long> b = Arrays.asList(17800074L, 17801438L, 17810682L, 17810690L, 17810758L, 17810774L, 3304009015L, 3304010186L, 3304010202L, 3305605414L, 3305743133L, 3305743175L, 3305757219L, 3305884764L, 3306677740L, 3306677792L, 3306732822L, 3306755360L, 3306755361L, 3306767584L, 3306767587L, 3306767590L, 3306767591L, 3306770602L, 3306770673L, 3306893758L, 3306999156L, 3307230482L, 3307406850L, 3307406851L, 3307406892L, 3307685305L, 3307685307L, 3307685315L, 3307857915L, 3307858000L, 3307858003L, 3307858046L, 3307858125L, 3307858133L, 3307858145L, 3307858147L, 3307858174L, 3307858202L, 3307858207L, 3308058477L, 3308285018L, 3308285043L, 3308285163L, 3308285297L, 3308525415L, 3308525527L, 3308525578L, 3308525682L, 3308525684L, 3308525701L, 3308525750L, 3308525785L, 3308827862L, 3308827951L, 3308828029L, 3309260638L, 3309260664L, 3309260673L, 3309794727L, 3309794728L, 3309794746L, 3801171096L, 3801171105L, 3801171112L, 3801171115L, 3801171117L, 3801183436L, 3801183551L, 3801184037L, 3801184233L, 3801184269L, 3801184273L, 3801197190L, 3801197191L, 3801197195L, 3801209404L, 3801209409L, 3801209421L, 3801210604L, 3801210989L, 3801210998L, 3801211003L, 3801211004L, 3801211019L, 3801225465L, 3801229665L, 3801229693L, 3801229731L, 3801247135L, 3801247149L, 3801247159L, 3801247162L, 3801247163L, 3801247166L, 3801247167L, 3801247168L, 3801247169L, 3801247172L, 3801247207L, 3801247208L, 3801330851L, 3801330863L, 3801330869L, 3801330889L, 3801330925L, 3801340094L, 3801349317L, 3801349319L, 3801392946L, 3801448874L, 3801463984L, 3801464001L, 3801536734L, 3801597123L, 3801740511L, 3801794915L, 3801829971L, 3801829972L, 3801829973L, 3801829974L, 3801842202L, 3802425636L, 3802425687L, 3802545174L, 3802547311L, 3802581996L, 3802582025L, 3802588051L);
//        for (Long aLong : a) {
//            if (!b.contains(aLong)) {
//                System.out.println(aLong);
//            }
//        }

//        System.out.println(System.nanoTime());
//        System.out.println(LocalDateTime.now().getNano());
//        System.out.println(ZonedDateTime.now().getNano());
//        System.out.println(System.currentTimeMillis());
//        System.out.println(IdGeneratorNew.nextId());


//        long timeMillis = System.currentTimeMillis();
//        System.out.println(timeMillis - 1483200000000L << 10 + 12 | 10298 << 12 | (1L & (long) ((1 << 12) - 1)));
//        System.out.println((timeMillis - 1483200000000L) << (10 + 12) | (10298 << 12) | (1L & (long) ((1 << 12) - 1)));
//        System.out.println(timeMillis - 1483200000000L);
//        System.out.println((timeMillis - 1483200000000L) << (10 + 12));
//        System.out.println(10298 << 12);
//        System.out.println(1L & (long) ((1 << 12) - 1));

//        testSeqId();
//        int a = 10;
//        int b = 3;
//        int avg = a / b;
//        System.out.println(avg);

//        long now = LocalDateTime.of(2022, 4, 28, 7, 59, 59).toInstant(ZoneOffset.of("+8")).toEpochMilli();
//        long dayMs = TimeUnit.DAYS.toMillis(1);
//        long l = now % dayMs;
//        long l1 = now - l - dayMs;
//        Date date = new Date(l1);
//
//        List<Boolean> booleanList = Lists.newArrayList(false, true, false, true);
//        System.out.println(booleanList.stream().sorted().collect(Collectors.toList()));

        int a = 44;
        int b = 7;
        System.out.println((int) Math.ceil((double) a / b));

        List<Integer> levels = Lists.newArrayList(3,4,5,1,2);
        System.out.println(getKey(7, levels));

        String format = String.format("本通电话仅扣%s钻，在第二分钟扣取", 10);
        System.out.println(format);
    }

    private static String getKey(int days, List<Integer> levels) {
        List<String> list = levels.stream().sorted().map(String::valueOf).collect(Collectors.toList());
        list.add(String.valueOf(days));
        return String.join("_", list);
    }

    public static void testSeqId() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<CompletableFuture<Long>> futureList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            CompletableFuture<Long> future = CompletableFuture.supplyAsync(IdGeneratorNew::nextId);
            futureList.add(future);
        }
        Set<Long> seqSet = new HashSet<>();
        for (CompletableFuture<Long> future : futureList) {
            Long seqId = future.get();
            log.info(seqId.toString());
            boolean add = seqSet.add(seqId);
            if (!add) {
                log.info("<----------------the same seqId-------------->" + seqId);
            }
        }
        log.info("size:{}", seqSet.size());
        log.info("cost:{}", System.currentTimeMillis() - start);
    }

    @Data
    public static class A {
        private String a;
        private String b;
    }

    @Data
    private static class B {
        private A a;
        private A b;
    }

    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        String extStr = "";
        while (in.hasNextLine()) {
            String s = extStr + in.nextLine();
            int length = s.length();
            if (length == 0) {
                continue;
            } else if (length < 8) {
                s = s + "0000000";
            } else {
                extStr = s.substring(8);
            }
            System.out.println(s.substring(0, 8));
        }
        while (extStr.length() > 0) {
            if (extStr.length() >= 8) {
                String s = extStr.substring(0, 8);
                System.out.println(s);
                extStr = extStr.replace(s, "");
            } else {
                extStr = extStr + "0000000";
                System.out.println(extStr.substring(0, 8));
                extStr = "";
            }
        }
    }

    private static int getLength(String str, String d) {
        int length = str.length();
        if (length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            if (Character.isDigit(s) || Character.isWhitespace(s)) {
                continue;
            }
            if (d.toUpperCase().toCharArray()[0] == Character.toUpperCase(s)) {
                count++;
            }
        }
        return count;
    }


}
