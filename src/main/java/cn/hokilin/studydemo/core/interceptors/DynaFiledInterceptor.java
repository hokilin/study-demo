package cn.hokilin.studydemo.core.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author linhuankai
 * @date 2020/12/11 17:40
 */
@Component
public class DynaFiledInterceptor implements HandlerInterceptor {

    private static HashMap<String, String> fieldRelationMap;

    static {
        fieldRelationMap = new HashMap<>(3);
        fieldRelationMap.put("gender", "extend1");
        fieldRelationMap.put("birthday", "extend2");
        fieldRelationMap.put("phone", "extend3");
    }
}
