package cn.hokilin.studydemo.utils;

import cn.hokilin.studydemo.dynamicfield.DynamicField;
import cn.hutool.core.bean.DynaBean;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author linhuankai
 * @date 2020/12/14 17:41
 */
@Slf4j
public class DynamicFieldUtils {

    /**
     * @param param
     * @param configs： ("extend1", "gender")
     *                 ("extend2", "birthday")
     *                 ("extend3", "phone")
     * @param <T>
     * @return
     */
    public static <T> T handleDynaFieldParam(T param, Map<String, String> configs) {
        // 将参数转换为jackson的JsonNode
        JsonNode jsonNode = JacksonUtils.valueToTree(param);
        List<Field> fields = new ArrayList<>();
        Class tempClass = param.getClass();
        //当父类为null的时候说明到达了最上层的父类(Object类)
        while (tempClass != null) {
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            //得到父类,然后赋给自己
            tempClass = tempClass.getSuperclass();
        }
        for (Field field : fields) {
            // 处理有@DynamicField注解的字段
            boolean annotationPresent = field.isAnnotationPresent(DynamicField.class);
            if (annotationPresent) {
                String fieldName = field.getName();
                String value = configs.get(fieldName);
                JsonNode node;
                if (value != null && (node = jsonNode.get(value)) != null) {
                    String textValue = node.textValue();
                    if (StringUtils.isEmpty(textValue)) {
                        throw new RuntimeException(value + "不允许为空");
                    }
                    // 利用反射设置extend值
                    DynaBean dynaBean = DynaBean.create(param);
                    dynaBean.set(fieldName, textValue);
                }
            }
        }
        return param;
    }
}
