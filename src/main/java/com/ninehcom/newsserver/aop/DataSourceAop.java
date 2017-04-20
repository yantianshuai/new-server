package com.ninehcom.newsserver.aop;

import com.ninehcom.common.utils.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * Created by zhangbin on 2017/4/18.
 */
@Aspect
@Configuration
public class DataSourceAop {

    private static final Logger log = Logger.getLogger(DataSourceAop.class.getName());

    @Value("${appid.}")
    private String appids;

    @Before("execution(* com.ninehcom.*.controller..*.*(..))")
    public void setDataSourceType(ProceedingJoinPoint thisJoinPoint){
        Object[] objects = thisJoinPoint.getArgs();
        int aa = appids.length();
        String[] paramNames = ((CodeSignature) thisJoinPoint.getStaticPart().getSignature()).getParameterNames();


    }

}
