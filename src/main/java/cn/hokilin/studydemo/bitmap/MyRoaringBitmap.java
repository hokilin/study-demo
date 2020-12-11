package cn.hokilin.studydemo.bitmap;

import org.roaringbitmap.RoaringBitmap;

/**
 * @author linhuankai
 * @date 2020/11/3 17:59
 */
public class MyRoaringBitmap {
    public static void main(String[] args) {
        demo1();
    }

    private static void demo1() {
        RoaringBitmap rb = RoaringBitmap.bitmapOf(1, 2, 3, 4, 7, 33, 55);
        //select 返回第几位的值
        System.out.println(rb.select(1));
        //rank 返回小于等于参数的值得个数
        System.out.println(rb.rank(55));
        //contains 是否包含参数
        System.out.println(rb.contains(56));
        //contains 是否包含参数
        System.out.println(rb.contains(5L, 56L));
        System.out.println(rb.contains(1L, 5L));
        //add 添加从左闭到右开区间内的值
        rb.add(10L, 15L);
        System.out.println(rb);
        System.out.println(rb.getLongCardinality());


        System.out.println("------------");

        RoaringBitmap rb1 = RoaringBitmap.bitmapOf(2, 3, 4, 44);
        System.out.println(rb1);
        //取两个bitmap的并集
        RoaringBitmap rb1or2 = RoaringBitmap.or(rb, rb1);
        System.out.println(rb1or2);
        //取两个bitmap的交集
        RoaringBitmap rb1and2 = RoaringBitmap.and(rb, rb1);
        System.out.println(rb1and2);
        rb.and(rb1);
        System.out.println(rb);
        //获取第一位
        System.out.println(rb.first());
    }
}
