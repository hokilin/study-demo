package cn.hokilin.studydemo.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author linhuankai
 * @date 2020/11/3 17:02
 */
public class TestBloomFilter {
    public static void main(String[] args) {
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 100, 0.01);
        bloomFilter.put(1);
        bloomFilter.put(2);
        bloomFilter.put(3);
        bloomFilter.put(4);
        bloomFilter.put(5);
        //判断过滤器内部是否存在对应的元素
        System.out.println(bloomFilter.mightContain(1));
        System.out.println(bloomFilter.mightContain(2));
        System.out.println(bloomFilter.mightContain(1000));
    }
}
