package cn.hokilin.studydemo.core.aop;

import cn.hokilin.studydemo.dynamicfield.DynamicField;
import cn.hokilin.studydemo.response.ResponseData;
import cn.hokilin.studydemo.utils.JacksonUtils;
import cn.hutool.core.bean.DynaBean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linhuankai
 * @date 2020/8/27 14:52
 */
@Component
@Aspect
@Slf4j
public class DynamicFieldAspect {

    private static HashMap<String, String> fieldRelationMap;

    static {
        fieldRelationMap = new HashMap<>(3);
        fieldRelationMap.put("gender", "extend1");
        fieldRelationMap.put("birthday", "extend2");
        fieldRelationMap.put("phone", "extend3");
    }

    @Pointcut("@annotation(cn.hokilin.studydemo.dynamicfield.DynamicField)")
    public void dynamicFieldForMethod() {
    }

    @Before("dynamicFieldForMethod()")
    public void before(JoinPoint joinPoint) {
        // 获取请求参数数组
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            // 处理有@DynamicField注解的参数
            if (arg.getClass().isAnnotationPresent(DynamicField.class)) {
                // 将参数转换为jackson的JsonNode
                JsonNode jsonNode = JacksonUtils.valueToTree(arg);
                for (Map.Entry<String, String> entry : fieldRelationMap.entrySet()) {
                    String key = entry.getKey();
                    JsonNode node = jsonNode.get(key);
                    if (node != null) {
                        // 利用反射设置extend值
                        DynaBean dynaBean = DynaBean.create(arg);
                        dynaBean.set(entry.getValue(), node.textValue());
                    } else {
                        throw new RuntimeException("参数" + key + "必传");
                    }
                }
            }
        }
    }

    @AfterReturning(value = "dynamicFieldForMethod()", returning = "returnObject")
    public void afterReturning(Object returnObject) {
        log.info("AOP AfterReturning 获取的返回值：" + returnObject);
        if (returnObject instanceof ResponseData) {
            ResponseData responseData = (ResponseData) returnObject;
            // 待处理的返回DTO
            Object onHandleData = responseData.getData();
            // 处理有@DynamicField注解的DTO
            if (onHandleData.getClass().isAnnotationPresent(DynamicField.class)) {
                // 将DTO转换为jackson的ObjectNode
                ObjectNode objectNode = JacksonUtils.valueToObjectNode(onHandleData);
                for (Map.Entry<String, String> entry : fieldRelationMap.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    JsonNode jsonNode = objectNode.get(value);
                    // 存在extend字段，则替换为实际业务字段，并删掉extend字段
                    if (jsonNode != null) {
                        objectNode.put(key, jsonNode.textValue());
                        objectNode.remove(value);
                    }
                }
                Object handledData = JacksonUtils.treeToValue(objectNode, Object.class);
                responseData.setData(handledData);
            }
        }
        log.info("AOP AfterReturning 处理后的返回值：" + returnObject);
    }
}
