package cn.hokilin.studydemo.bitmap;

/**
 * @author linhuankai
 * @date 2021/2/27 22:47

 */
public class MyBitmapOfInt {

    private static int[] bitsMap;

    /**
     * 构造函数中传入数据中的最大值
     *
     * @param length
     */
    public MyBitmapOfInt(long length) {
        // 根据长度算出，所需数组大小
        bitsMap = new int[(int) (length >> 5) + ((length & 31) > 0 ? 1 : 0)];
    }

    public int getBit(long index) {
        int intData = bitsMap[(int) (index >> 5)];
        int offset = (int) (index & 31);
        return intData >> offset & 0x01;
    }


    public void setBit(long index) {
        // 求出该index所在bitMap的下标
        int belowIndex = (int) (index >> 5);
        // 求出该值的偏移量(求余)
        int offset = (int) (index & 31);
        bitsMap[belowIndex] |= (0x01 << offset);
    }

    public static void main(String[] args) {
        MyBitmapOfInt bitMap = new MyBitmapOfInt(32);
        bitMap.setBit(32);
        System.out.println(bitMap.getBit(1));
        System.out.println(bitMap.getBit(32));
    }
}
