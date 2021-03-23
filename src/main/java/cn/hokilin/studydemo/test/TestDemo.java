package cn.hokilin.studydemo.test;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

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

        String s = "1234567844444";
        System.out.println("s:" + s.substring(8) + "," + s);
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
