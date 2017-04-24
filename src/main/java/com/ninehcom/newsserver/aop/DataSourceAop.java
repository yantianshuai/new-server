package com.ninehcom.newsserver.aop;

import com.ninehcom.newsserver.conf.AppIdConfig;
import com.ninehcom.newsserver.conf.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Logger;

@Aspect
@Component
public class DataSourceAop {
    private static final Logger log = Logger.getLogger(DataSourceAop.class.getName());

    @Autowired
    AppIdConfig appIdConfig;

    @Around("execution(* com.ninehcom.newsserver.controller..*.*(..))")
    public Object setWriteDataSourceType(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        //获取参数值
        Object[] objects = thisJoinPoint.getArgs();
        //获取传入的参数名
        String[] paramNames = ((CodeSignature) thisJoinPoint.getStaticPart().getSignature()).getParameterNames();
        //获取配置表中对应的appid对应表
        Map<String,String> appIdMap = appIdConfig.getApp_id();
        for (int i = 0; i < paramNames.length; i++) {
            if ("appId".equals(paramNames[i])){
                String dasourceType= appIdMap.get(String.valueOf(objects[i]));
                log.info("dasourceType========="+dasourceType);
                DataSourceContextHolder.setDataSource(dasourceType);
            }
        }
        return thisJoinPoint.proceed();
    }
}
