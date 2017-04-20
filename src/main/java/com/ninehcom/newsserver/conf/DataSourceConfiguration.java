package com.ninehcom.newsserver.conf;

import com.ninehcom.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by zhangbin on 2017/4/18.
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    private static final Logger log = Logger.getLogger(DataSourceConfiguration.class.getName());

    @Value("${datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "shenhuaDataSource")
    @ConfigurationProperties(prefix = "datasource.shenhua")
    public DataSource getShenhuaDataSource(){
        log.info("-----------------shenhuaDataSource init --------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "taidaDataSource")
    @ConfigurationProperties(prefix = "datasource.taida")
    public DataSource getTaidaDataSource(){
        log.info("-------------------datasource.taida---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "guoanDataSource")
    @ConfigurationProperties(prefix = "datasource.guoan")
    public DataSource getGuoanDataSource(){
        log.info("--------------------datasource.guoan------------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "getDataSources")
    public Map<Object,Object> getDataSources(){
        Map<Object,Object> targetDataSources = new HashMap<>();
            targetDataSources.put(DataSourceType.shenhuaDB.getType(),getShenhuaDataSource());
            targetDataSources.put(DataSourceType.taidaDB.getType(),getTaidaDataSource());
            targetDataSources.put(DataSourceType.guoanDB.getType(),getGuoanDataSource());
        return targetDataSources;
    }
}
