package cn.hokilin.studydemo.bitmap;

/**
 * @author linhuankai
 * @date 2020/11/3 16:04
 */
public class MyBitMap {

    private byte[] bytes;

    public MyBitMap(int size) {
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
        MyBitMap myBitMap = new MyBitMap(32);
        myBitMap.set(30);
        myBitMap.set(13);
        myBitMap.set(24);
        System.out.println(myBitMap.contain(30));
    }

}
