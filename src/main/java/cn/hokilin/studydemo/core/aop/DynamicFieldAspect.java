package cn.hokilin.studydemo.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author linhuankai
 * @date 2020/8/27 14:52
 */
@Component
@Aspect
@Slf4j
public class DynamicFieldAspect {

    @Pointcut("@annotation(cn.hokilin.studydemo.dynamicfield.DynamicField)")
    public void dynamicFieldForMethod() {
    }

    @Before("dynamicFieldForMethod()")
    public void before(JoinPoint joinPoint) {

    }
}
