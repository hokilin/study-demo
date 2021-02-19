package cn.hokilin.studydemo.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.nio.charset.StandardCharsets;

/**
 * @author linhuankai
 * @date 2021/1/31 15:31
 */
public class FastJsonSerialization implements Serialization {
    @Override
    public byte[] serialize(Object data) {
        SerializeWriter out = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(out);
        serializer.config(SerializerFeature.WriteEnumUsingToString, true);
        serializer.config(SerializerFeature.WriteClassName, true);
        serializer.write(data);
        return out.toBytes(StandardCharsets.UTF_8);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clz) {
        return JSON.parseObject(new String(data), clz);
    }

    @Override
    public <T> T deserialize(byte[] bytes) {
        return null;
    }
}
