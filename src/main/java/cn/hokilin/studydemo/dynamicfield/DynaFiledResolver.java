package cn.hokilin.studydemo.dynamicfield;

import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author linhuankai
 * @date 2020/12/11 14:39
 */
@Component
public class DynaFiledResolver implements HandlerMethodArgumentResolver {

    private static HashMap<String, String> fieldRelationMap;

    static {
        fieldRelationMap = new HashMap<>(3);
        fieldRelationMap.put("gender", "extend1");
        fieldRelationMap.put("birthday", "extend2");
        fieldRelationMap.put("phone", "extend3");
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(DynamicField.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Class<?> parameterType = parameter.getParameterType();
        Object resultObject = BeanUtils.instantiateClass(parameterType);

        //未经过处理的字段名
        String sourceTemp;
        //经过处理的字段名
        String executeTemp;
        //传参的值
        String[] values;
        //得到bean中的方法
        Field[] fields = parameter.getParameterType().getDeclaredFields();
        for (Iterator<String> itr = webRequest.getParameterNames(); itr.hasNext(); ) {
            sourceTemp = itr.next();
            executeTemp = sourceTemp;
        }
        return null;
    }
}
