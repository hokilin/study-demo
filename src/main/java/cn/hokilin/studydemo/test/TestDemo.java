package cn.hokilin.studydemo.test;


import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author linhuankai
 * @date 2020/11/24 15:27
 */
public class TestDemo {
    public static void main(String[] args) {
//        String encryptedPhone = DigestUtils.md5Hex("123456" + "123456" + "15222892752" + "123456");
//        encryptedPhone = DigestUtils.md5Hex(encryptedPhone.toUpperCase()).toUpperCase();
//        System.out.println(encryptedPhone);
//
//        String encryptedPhone1 = DigestUtils.md5Hex(DigestUtils.md5Hex(("tmall" + "15222892752" + "123456")));
//        System.out.println(encryptedPhone1);

//        Date date = DateUtil.parse("1995-03-02 02:02:02", "yyyy-MM-dd");
//        System.out.println(date);

        System.out.println(isLegalDateStr("2020-02-29"));
        System.out.println(isLegalDateTimeStr("2020-02-28 00:00:01"));
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
