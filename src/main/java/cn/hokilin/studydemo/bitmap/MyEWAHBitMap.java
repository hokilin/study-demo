package cn.hokilin.studydemo.bitmap;

import com.googlecode.javaewah.EWAHCompressedBitmap;

import java.util.Iterator;

/**
 * @author linhuankai
 * @date 2020/11/3 16:38
 */
public class MyEWAHBitMap {

    public static void main(String[] args) {
        EWAHCompressedBitmap bitmap = new EWAHCompressedBitmap();
//        bitmap.addWord(1);
//        bitmap.addWord(2);
//        bitmap.addWord(3);
//        bitmap.addWord(4);
        bitmap.set(5);
        bitmap.set(6);
        bitmap.set(7);
        bitmap.set(8);
//        bitmap.addLiteralWord(9);
//        bitmap.addLiteralWord(10);
//        bitmap.addLiteralWord(11);
        System.out.println(bitmap);
    }
}
