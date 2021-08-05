package cn.hokilin.studydemo.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import org.roaringbitmap.RoaringBitmap;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collectors;

/**
 * @author linhuankai
 * @date 2020/11/24 15:27
 */
public class TestDemo {
    public static void main(String[] args) throws JsonProcessingException, InterruptedException {
//        String encryptedPhone = DigestUtils.md5Hex("123456" + "123456" + "15222892752" + "123456");
//        encryptedPhone = DigestUtils.md5Hex(encryptedPhone.toUpperCase()).toUpperCase();
//        System.out.println(encryptedPhone);
//
//        String encryptedPhone1 = DigestUtils.md5Hex(DigestUtils.md5Hex(("tmall" + "15222892752" + "123456")));
//        System.out.println(encryptedPhone1);

//        Date date = DateUtil.parse("1995-03-02 02:02:02", "yyyy-MM-dd");
//        System.out.println(date);

//        System.out.println(isLegalDateStr("2020-02-29"));
//        System.out.println(isLegalDateTimeStr("2020-02-28 00:00:01"));

//        String str = "{" +
//                "    \"7001_001\": \"4\"," +
//                "    \"7001_002\": \"0\"," +
//                "    \"7001_003\": \"4\"," +
//                "    \"7001_004\": \"1\"}";
//        System.out.println(str);
//        ObjectMapper mapper = new ObjectMapper();
//        Map map = mapper.readValue(str, Map.class);
//        Map map1 = JacksonUtils.string2Obj(str, Map.class);
//        System.out.println(map);
//        System.out.println(map1);

//        String name = "TP-sss";
//        System.out.println(name.startsWith("TP-"));

//        BlockingQueue<Integer> queue = new
//                SynchronousQueue<>();
//        System.out.print(queue.offer(1) + " ");
//        System.out.print(queue.offer(2) + " ");
//        System.out.print(queue.offer(3) + " ");
//        System.out.print(queue.take() + " ");
//        System.out.println(queue.size());

//        String s = "1234567844444";
//        System.out.println("s:" + s.substring(8) + "," + s);

        String bitmap = "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAJsAqgDhAQ8CHgIvAqECvwLYA90D\"}";
        RoaringBitmap roaringBitmap = deserializeFromJsonStr(bitmap);
        System.out.println(Arrays.toString(roaringBitmap.toArray()));

        String bitmap1 = "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAsAqAAnAYABrAEvAk8CaQJcA5QD\"}";
        RoaringBitmap roaringBitmap1 = deserializeFromJsonStr(bitmap1);
        System.out.println(Arrays.toString(roaringBitmap1.toArray()));
//
//        String bitmap2 = "{\"bytes\":\"OjAAAAEAAAAAAAEAEAAAAAEAAwA=\"}";
//        RoaringBitmap roaringBitmap2 = deserializeFromJsonStr(bitmap2);
//        System.out.println(Arrays.toString(roaringBitmap2.toArray()));
//
//        String bitmap3 = "{\"bytes\":\"OjAAAAEAAAAAAAQAEAAAAAQABQAGAAoADwA=\"}";
//        RoaringBitmap roaringBitmap3 = deserializeFromJsonStr(bitmap3);
//        System.out.println(Arrays.toString(roaringBitmap3.toArray()));
//
//        String bitmap4 = "{\"bytes\":\"OjAAAAEAAAAAAAAAEAAAAA0A\"}";
//        RoaringBitmap roaringBitmap4 = deserializeFromJsonStr(bitmap4);
//        System.out.println(Arrays.toString(roaringBitmap4.toArray()));
//
//        String bitmap5 = "{\"bytes\":\"OjAAAAEAAAAAAAAAEAAAAAgA\"}";
//        RoaringBitmap roaringBitmap5 = deserializeFromJsonStr(bitmap5);
//        System.out.println(Arrays.toString(roaringBitmap5.toArray()));
//
//        RoaringBitmap availableCodeBitmap = getAvailableCodeBitmap(1000, 10, roaringBitmap3);
//        System.out.println(Arrays.toString(availableCodeBitmap.toArray()));

        String codeListStr = "[{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAIAUwA7AXABdAHRAaQCsQK9AvgC\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAACAAFQHyAVACiQKLAsQCygIiAzsD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAEMAVgAzAU4BnwGiATQCbQLyAqUD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAG4AjgA2AV8BagFsAXMC4QIvAzUD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAPgAhgG8AdgB6QEfAjYCdwJaA3kD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAJUB0AH0AVsCaAKUAqYC8AJxA6oD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAKQA2QARAbIB+gEzAmsCsALRAqMD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAABYAuQAAASgBvQG+AY8CrAIAA2AD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAEwAdgA6AZEB1QFBAn0C9AIEA6sD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAEAA3QATAdsB8AFdAskCGgNQA4sD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAYAMQBxAN8AYwKBAsUCWwN2A+gD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAEAHABGAGgAGgE+AgIDaQO4A9YD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAF0AhQDuAEAB5gFZAuQCLQMwA80D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAOcAZQF9AYcBnAEnAlcCZgLMAvoC\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAB4AIwDeAOMA6QD1ABwBBgOJA8MD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAgAggDcAAYC4wJZA2gDbQPOA9ID\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAFEAVwCUAagBKgJVAowCmALbAtcD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAJgAcgG7AcEB7QFfAkIDkwO+A+YD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAEEAcACRAJ8ArADAAAkB0gFNAlIC\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAABMAWQB3AIoAlwDxABACzgI+A3ID\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAABQBLAHlASACNQI4AnACrQJAA+UD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAA0ApQDoAPYANQGLAXECngLHAjID\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAADYAPQBIAL8BLAI8AnwCcwOsA78D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAADsABgF6AZ4BXAKdArICygPLA9QD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAIYAywD3AEEBeALiAiMDZwPIA+MD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAUAEAAzAAgBAwLBAiUDOANsA30D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAHIADgFSAZABFwIiAq8CtQIxA0gD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAJgBmQGbAQ4CmwIKAysDfwOQA+ID\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAHoAewCwAEUBUAGlAq4C1wImA+QD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAP0ABwHOAaIC0AL8AgwDqAOpA64D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAA4AbQB0ADEBUQF4Af4BhQK5At0C\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAGkAeACpAOEA5ACrAfgBTwNwA3QD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAACsANQBPANsAKgFdAZoBdgL5AgMD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAADwASgBaAKEAzgDgAPMAZwH1ARIC\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAJsAqgDhAQ8CHgIvAqECvwLYA90D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAALYAKwEuAWkBggGwAcsB7gIoA4ED\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAA8AUgCtABABDAIuAkUC2QJKA6cD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAACQAJgBeAAEBAwELAUgBrwH+As8D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAEQAmQD+AEkBoAEFAkkCyAIhA9wD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAACcALADiAFQBZAGlAWcCHgORA6QD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAABoA+QAFAWAB7wEdAk4CVgLGAuoC\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAACgAtQDvAOsBFAKaAs8CBQMNA3oD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAMIAPwHHAQgCowK0Au8CEwMsA2YD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAB8AIgDPABgBcwGzAcgB0wGfAsED\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAABQAzQETAiUCOgJsApkCRAPGA9UD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAsAqAAnAYABrAEvAk8CaQJcA5QD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAwBkgEWAm4C5wJiA3cDewOKA48D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAADcAWAHJAeoBUwJhAnUCgAL1AvYC\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAADkAZgBvAIQAewGPAakBtQGRAhED\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAIMAiQDHAB4BrQHDAeIBjQKdA94D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAkAPwC0AdQBCgJCAt8C/QIBA28D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAE0AYwB/AI0AYgEOA18DlQPEA8kD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAFAAxQDTAOoA9AAbAR4C0gIWAx8D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAGoA/ABKAacBLQI3AmICPwOOA70D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAABgAGQAvAA0CigL/AjwDYQOfA8ID\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAADkBPAExAkQCkALlAugC8wIIA1gD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAcArwC4ADQBWwF8AeABGALNAt0D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAH0AlADVAHYBjgE/AocCUgNdA34D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAJwAogApAW0BcgKqAjYDTAOFA+AD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAQACgCrAEMBwgHXAWUCagKTAg8D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAABUAGwCbAPIAFwEvAdoB7AHHA8wD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAFUAugDNAGQCuwK8AgkDVQNjA2UD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAACMBNwFNAW8B5AECAksCdAIqA7sD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAMAkAAEAUIBRAFrAXkBtwGSAuAC\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAADAADQEdAU8BXAGhAV4ChgLTA+ED\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAACUAZQCBAA8BgQEbAlQCGQOXA5sD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAADIAfAAWAVUBcQHcARkCjQO0A+cD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAACkAlQC3AD0BtgFvAqgCtgK4ApoD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAIgAtAAhASQBRwGEAZ0BAQLDAuYC\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAGIAxAD6AFoBXgEAAgsCvgJkA7MD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAACoAWABkAJoALQFMAnsCBwOgA8UD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAADoAYQBnAHkAiwASAR8BlgEJAq8D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAEUAWwA+AYwB9wH9AcsC1AJDA8AD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAI8AkwDGANIAIgFjAaYBWAJ5At8D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAB0AVADmABkBOAGqAQQCQwI9A7wD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAE4AowB1AdkBKQLYAoQDmAPQA9ED\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAD4AdQBGAX4BBwIjAjkCWgKMA5wD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAADgASwBfAJcBugHFAdUC7QI0A7YD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAKcAygAwAXcB+QH/ASgC6wJRA5YD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAALEA2AD/ANYBHAKnArcCwgLTAvsC\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAKYArgDQAOEB7gFgAhgDSwNTA9kD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAJ4AvgDaAPsAIAFmAd4BgwLaAmsD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAJIAvADDANcA7QACAaQB8QI3A6YD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAAwAgwGjAc8B3QEkAkoCiAKOApID\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAACEAQgDJAFkB/AGpAt4CVgNuA4AD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAJ0AwQDRAMABxgFHAn4CFANGA7ID\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAEkA5QBoAYUB3wEyAkgCvwL3AlQD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAGsAvwBMAY0BEQJGAgsDEAM6A7AD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAHMAoADMAIgBGgI9ApUCoAK6AiAD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAOsAJgFhAfEBqwIkA0cDagN4A6ED\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAABcAXAC4AcoBOwIbAykDOQNeA7cD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAIAAVgF/AbEBlwLsAhIDHAOZA7ED\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAC4ARwCqALMAbgGuARUDJwNBA1cD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAABEANABgAOMBggKEAk0DTgOGA6ID\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAC0AbAC9ANYAJQH2AfsBJgLpAjMD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAABIAyAAyAecBKwIXAy4DdQODA54D\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAOwAUwHMARUCMAKcAqECswLAAtoD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAALIAuwAKAcQBDwLcAh0DRQOtA7UD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAkAEAAAAIwA1ACTAUACUQKWAoIDiAO5A9sD\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAgAEAAAAIcAlgCJAbkBIQJ6AkkDugPYAw==\"},\n" +
                "{\"bytes\":\"OjAAAAEAAAAAAAoAEAAAAH4A8ABLAVcBigHoAfMBfwLWAnwDhwM=\"}]";
        List<String> codeList = JSON.parseArray(codeListStr, String.class);
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < codeList.size(); i++) {
            String code = codeList.get(i);
            RoaringBitmap roaringBitmap6 = deserializeFromJsonStr(code);
            int[] array = roaringBitmap6.toArray();
            List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
            System.out.println(i + ":" + list);
            if (!Collections.disjoint(integerList, list)) {
                System.out.println("--------------------");
                System.out.println(i + ":" + list);
                System.out.println("--------------------");
            }
            integerList.addAll(list);
        }
    }

    public static RoaringBitmap deserializeFromJsonStr(String jsonStr) {
        try {
            if (jsonStr == null || jsonStr.length() == 0) {
                return new RoaringBitmap();
            }
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            byte[] bytes = jsonObject.getBytes("bytes");
            RoaringBitmap bitmap = new RoaringBitmap();
            if (bytes != null && bytes.length > 0) {
                bitmap.deserialize(ByteBuffer.wrap(bytes));
            }
            return bitmap;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static RoaringBitmap getAvailableCodeBitmap(Integer totalCount, Integer buyCount, RoaringBitmap soldCodeBitmap) {
        RoaringBitmap availableBitmap = new RoaringBitmap();
        Random random = new Random();
        // 0号不参与出售，这里保证0号已占用
        soldCodeBitmap.add(0);
        //反转所有位，获得未售号码
        soldCodeBitmap.flip(0L, (long) totalCount + 1);
        // 未售号码列表
        int[] codeArr = soldCodeBitmap.toArray();
        int length = codeArr.length;
        if (length >= buyCount) {
            for (int i = 0; i < buyCount; ++i) {
                int index = random.nextInt(length);
                //避免重复
                while (availableBitmap.contains(codeArr[index])) {
                    index = random.nextInt(length);
                }
                //添加可购号码
                availableBitmap.add(codeArr[index]);
                //置为已售
                soldCodeBitmap.flip(codeArr[index]);
            }
        }
        //恢复为已售号码
        soldCodeBitmap.flip(0L, (long) totalCount + 1);
        return availableBitmap;
    }

    public static boolean isLegalDateStr(String dateStr) {
        String patten = "yyyy-MM-dd";
        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(patten) || dateStr.length() != patten.length()) {
            return false;
        }
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(patten);
            LocalDate localDate = LocalDate.parse(dateStr, dateTimeFormatter);
            return dateStr.equals(localDate.format(dateTimeFormatter));
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isLegalDateTimeStr(String dateStr) {
        String patten = "yyyy-MM-dd HH:mm:ss";
        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(patten) || dateStr.length() != patten.length()) {
            return false;
        }
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(patten);
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, dateTimeFormatter);
            return dateStr.equals(dateTime.format(dateTimeFormatter));
        } catch (Exception e) {
            return false;
        }
    }
}
