package com.ninehcom.newsserver.conf;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by zhangbin on 2017/4/20.
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        Object resultObject;
        //返回当前线程调用的数据源
        String typeKey = DataSourceContextHolder.getJdbcType();
        logger.info("数据源切换到------>"+typeKey);
        return typeKey;
    }
}
