package cn.hokilin.studydemo.test;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author linhuankai
 * @date 2020/11/24 15:27
 */
public class TestDemo {
    public static void main(String[] args) {
        String encryptedPhone = DigestUtils.md5Hex("123456" + "123456" + "15222892752" + "123456");
        encryptedPhone = DigestUtils.md5Hex(encryptedPhone.toUpperCase()).toUpperCase();
        System.out.println(encryptedPhone);

        String encryptedPhone1 = DigestUtils.md5Hex(DigestUtils.md5Hex(("tmall" + "15222892752" + "123456")));
        System.out.println(encryptedPhone1);
    }
}
