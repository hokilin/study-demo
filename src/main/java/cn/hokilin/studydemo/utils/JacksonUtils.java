package cn.hokilin.studydemo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author linhuankai
 * @date 2020/9/8 17:10
 */
@Slf4j
public class JacksonUtils {
    private volatile static ObjectMapper OBJECT_MAPPER;

    private JacksonUtils() {
    }

    static {
        ObjectMapper mapper = null;
        try {
            mapper = SpringUtils.getBean(ObjectMapper.class);
        } catch (Exception e) {
            log.info("从spring容器中获取ObjectMapper实例失败");
        }
        if (mapper == null) {
            synchronized (JacksonUtils.class) {
                if (mapper == null) {
                    mapper = new ObjectMapper();
                    // 忽略多余字段
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    // 空数组反序列化
                    mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
                    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

                    // 时间反序列化
                    mapper.registerModule(new JavaTimeModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                }
            }
        }
        OBJECT_MAPPER = mapper;
    }

    public static <T> String obj2String(T src) {
        if (src == null) {
            return null;
        }
        try {
            return src instanceof String ? (String) src : OBJECT_MAPPER.writeValueAsString(src);
        } catch (Exception e) {
            log.error("Parse Object to String error src=" + src, e);
            return null;
        }
    }

    public static <T> byte[] obj2Byte(T src) {
        if (src == null) {
            return null;
        }
        try {
            return src instanceof byte[] ? (byte[]) src : OBJECT_MAPPER.writeValueAsBytes(src);
        } catch (Exception e) {
            log.error("Parse Object to byte[] error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        str = escapesSpecialChar(str);
        try {
            return clazz.equals(String.class) ? (T) str : OBJECT_MAPPER.readValue(str, clazz);
        } catch (Exception e) {
            log.error("Parse String to Object error\nString: {}\nClass<T>: {}\nError: {}", str, clazz.getName(), e);
            return null;
        }
    }

    public static <T> List<T> string2ObjList(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(str, OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, clazz));
        } catch (Exception e) {
            log.error("Parse String to Object List error\nString: {}\nClass<T>: {}\nError: {}", str, clazz.getName(), e);
            return null;
        }
    }

    public static <T> T byte2Obj(byte[] bytes, Class<T> clazz) {
        if (bytes == null || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(byte[].class) ? (T) bytes : OBJECT_MAPPER.readValue(bytes, clazz);
        } catch (Exception e) {
            log.error("Parse byte[] to Object error\nbyte[]: {}\nClass<T>: {}\nError: {}", bytes, clazz.getName(), e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        str = escapesSpecialChar(str);
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : OBJECT_MAPPER.readValue(str, typeReference));
        } catch (Exception e) {
            log.error("Parse String to Object error\nString: {}\nTypeReference<T>: {}\nError: {}", str,
                    typeReference.getType(), e);
            return null;
        }
    }

    public static <T> T byte2Obj(byte[] bytes, TypeReference<T> typeReference) {
        if (bytes == null || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(byte[].class) ? bytes : OBJECT_MAPPER.readValue(bytes, typeReference));
        } catch (Exception e) {
            log.error("Parse byte[] to Object error\nbyte[]: {}\nTypeReference<T>: {}\nError: {}", bytes,
                    typeReference.getType(), e);
            return null;
        }
    }

    public static <T> T map2Obj(Map<String, String> map, Class<T> clazz) {
        String str = obj2String(map);
        return string2Obj(str, clazz);
    }

    private static String escapesSpecialChar(String str) {
        return str.replace("\n", "\\n").replace("\r", "\\r");
    }

    public static JsonNode valueToTree(Object object) {
        return OBJECT_MAPPER.valueToTree(object);
    }

    public static ObjectNode valueToObjectNode(Object object) {
        return OBJECT_MAPPER.valueToTree(object);
    }

    public static <T> T treeToValue(TreeNode treeNode, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.treeToValue(treeNode, valueType);
        } catch (JsonProcessingException e) {
            log.error("Parse treeNode to Object error\ntreeNode: {}\nTypeReference<T>: {}\nError: {}", treeNode,
                    valueType, e);
            return null;
        }
    }
}
