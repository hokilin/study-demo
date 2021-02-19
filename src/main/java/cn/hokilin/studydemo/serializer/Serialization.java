package cn.hokilin.studydemo.serializer;

import java.io.IOException;

/**
 * @author linhuankai
 * @date 2021/1/31 12:51
 */
public interface Serialization {
    byte[] serialize(Object obj) throws IOException;

    <T> T deserialize(byte[] bytes, Class<T> clz) throws IOException;

    <T> T deserialize(byte[] bytes) throws IOException;
}
