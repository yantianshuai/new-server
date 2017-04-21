package com.ninehcom.newsserver.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Administrator on 2016/11/11.
 */
@Configuration
@EnableTransactionManagement
public class MyDataSourceTransactionManagerAutoConfiguration extends DataSourceTransactionManagerAutoConfiguration {
    @Autowired
    @Qualifier("routingDataSource")
    AbstractRoutingDataSource abstractRoutingDataSource;

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManagers() {
        System.out.println("-------------------- transactionManager init ---------------------");
        return new DataSourceTransactionManager(abstractRoutingDataSource);
    }
}
