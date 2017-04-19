package com.ninehcom.newsserver.aop;

import com.ninehcom.common.utils.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * Created by zhangbin on 2017/4/18.
 */
@Aspect
@Component
public class DataSourceAop {

    private static final Logger log = Logger.getLogger(DataSourceAop.class.getName());

    @Around("execution(* com.ninehcom.*.service..*.find*(..)) || execution(* com.ninehcom.*.service..*.get*(..)) || execution(* com.ninehcom.*.service..*.select*(..))")
    public Object twiceAsOld(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        final String methodName = thisJoinPoint.getSignature().getName();
        final MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
        Method method = methodSignature.getMethod();

        Transactional annotation = method.getAnnotation(Transactional.class);
        if (annotation == null) {
            DataSourceContextHolder.read();
            log.info("---dataSource switch to：slaver---");
        } else {
            DataSourceContextHolder.write();
            log.info("---transaction dataSource switch to：master---");
        }
        return thisJoinPoint.proceed();
    }

}
