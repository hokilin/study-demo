package cn.hokilin.studydemo.bitmap;

import java.util.BitSet;

/**
 * @author linhuankai
 * @date 2020/11/3 16:04
 */
public class MyBitMapOfByte {

    private byte[] bytes;

    public MyBitMapOfByte(int size) {
        if (size <= 0) {
            return;
        }
        int initSize = size / (8) + 1;
        bytes = new byte[initSize];
    }

    public void set(int number) {
        //相当于对一个数字进行右移动3位，相当于除以8
        int index = number >> 3;
        //相当于 number % 8 获取到byte[index]的位置
        int position = number & 0x07;
        //进行|或运算  参加运算的两个对象只要有一个为1，其值为1。
        bytes[index] |= 1 << position;
    }


    public boolean contain(int number) {
        int index = number >> 3;
        int position = number & 0x07;
        return (bytes[index] & (1 << position)) != 0;
    }

    public static void main(String[] args) {
        MyBitMapOfByte myBitMapOfByte = new MyBitMapOfByte(32);
        myBitMapOfByte.set(30);
        myBitMapOfByte.set(13);
        myBitMapOfByte.set(30);
        System.out.println(myBitMapOfByte.contain(30));

        //jdk bitSet
        BitSet bitSet = new BitSet(100);
        bitSet.set(999);
        boolean b = bitSet.get(25);
        System.out.println(b);
    }

}
