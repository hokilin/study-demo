package cn.hokilin.studydemo.core.aop;

import cn.hokilin.studydemo.dynamicfield.DynamicField;
import cn.hokilin.studydemo.utils.JacksonUtils;
import cn.hutool.core.bean.DynaBean;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
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
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg.getClass().isAnnotationPresent(DynamicField.class)) {
                JsonNode jsonNode = JacksonUtils.valueToTree(arg);
                for (Map.Entry<String, String> entry : fieldRelationMap.entrySet()) {
                    JsonNode node = jsonNode.get(entry.getKey());
                    if (node != null) {
                        DynaBean dynaBean = DynaBean.create(arg);
                        dynaBean.set(entry.getValue(), node.textValue());
                    }
                }
            }
        }
    }
}
