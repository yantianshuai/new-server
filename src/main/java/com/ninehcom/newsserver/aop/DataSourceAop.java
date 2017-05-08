package com.ninehcom.newsserver.aop;

import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.untils.Result;
import com.ninehcom.newsserver.conf.AppIdConfig;
import com.ninehcom.newsserver.conf.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;
import java.util.logging.Logger;

@Aspect
@Component
public class DataSourceAop {
    private static final Logger log = Logger.getLogger(DataSourceAop.class.getName());

    @Autowired
    AppIdConfig appIdConfig;

    @Around("execution(* com.ninehcom.newsserver.controller..*.*(..))")
    public Object setWriteDataSourceType(ProceedingJoinPoint thisJoinPoint) throws Throwable,Exception {
        //获取传入的参数值
        List paramValueList = Arrays.asList(thisJoinPoint.getArgs());
        //获取传入的参数名
        List paramNamesList = Arrays.asList(((CodeSignature) thisJoinPoint.getStaticPart().getSignature()).getParameterNames());
        //传入的参数名和参数值装配成Map
        Map<String,String> paramValueMap = new HashMap<>();
        for(int i=0;i<paramNamesList.size();i++){
            paramValueMap.put(String.valueOf(paramNamesList.get(i)),String.valueOf(paramValueList.get(i)));
        }

        //获取配置表中对应的appid对应表
        Map<String,String> appIdMap = appIdConfig.getApp_id();

        //获取传入的AppId参数对应的值
        String paramAppIdValue = paramValueMap.get("appId");

        if(appIdMap.containsKey(paramAppIdValue)){
            String dasourceType = appIdMap.get(paramAppIdValue);
            log.info("dasourceType========="+dasourceType);
            DataSourceContextHolder.setDataSource(dasourceType);
        }else{
            throw new Exception(ErrorCode.Fail.getMessage());
        }
        return thisJoinPoint.proceed();
    }

}
