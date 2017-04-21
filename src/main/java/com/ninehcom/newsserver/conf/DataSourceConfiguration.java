package com.ninehcom.newsserver.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ninehcom.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by zhangbin on 2017/4/18.
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration{

    private static final Logger log = Logger.getLogger(DataSourceConfiguration.class.getName());
    @Value("${datasource.guoan.url}")
    private String gaUrl;
    @Value("${datasource.shenhua.url}")
    private String shUrl;
    @Value("${datasource.taida.url}")
    private String tdUrl;


    @Value("${datasource.guoan.driverClass}")
    private String gaDriverClass;
    @Value("${datasource.shenhua.driverClass}")
    private String shDriveClass;
    @Value("${datasource.taida.driverClass}")
    private String tdDriveClass;


    @Value("${datasource.guoan.username}")
    private String gaUser;
    @Value("${datasource.shenhua.username}")
    private String shUser;
    @Value("${datasource.taida.username}")
    private String tdUser;


    @Value("${datasource.guoan.password}")
    private String gaPass;
    @Value("${datasource.shenhua.password}")
    private String shPass;
    @Value("${datasource.taida.password}")
    private String tdPass;


//    @Bean(name = "shenhuaDataSource")
//    @ConfigurationProperties(prefix = "datasource.shenhua")
//    public DataSource getShenhuaDataSource(){
//        log.info("-----------------shenhuaDataSource init --------------------");
//        return DataSourceBuilder.create().type(dataSourceType).build();
//    }
//
//    @Bean(name = "taidaDataSource")
//    @ConfigurationProperties(prefix = "datasource.taida")
//    public DataSource getTaidaDataSource(){
//        log.info("-------------------datasource.taida---------------------");
//        return DataSourceBuilder.create().type(dataSourceType).build();
//    }
//
//    @Bean(name = "guoanDataSource")
//    @ConfigurationProperties(prefix = "datasource.guoan")
//    public DataSource getGuoanDataSource(){
//        log.info("--------------------datasource.guoan------------------------");
//        return DataSourceBuilder.create().type(dataSourceType).build();
//    }
//
//    @Bean(name = "getDataSources")
//    public Map<Object,Object> getDataSources(){
//        Map<Object,Object> targetDataSources = new HashMap<>();
//            targetDataSources.put(DataSourceType.shenhuaDB.getType(),getShenhuaDataSource());
//            targetDataSources.put(DataSourceType.taidaDB.getType(),getTaidaDataSource());
//            targetDataSources.put(DataSourceType.guoanDB.getType(),getGuoanDataSource());
//        return targetDataSources;
//    }

    public DataSource createDataSource(String url,String driveClass,String user,String pass) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setJdbcUrl(url);
            dataSource.setDriverClass(driveClass);
            dataSource.setUser(user);
            dataSource.setPassword(pass);
            dataSource.setInitialPoolSize(5);
            dataSource.setMinPoolSize(2);
            dataSource.setMaxPoolSize(10);
            dataSource.setIdleConnectionTestPeriod(3000);
        return dataSource;
    }

    @Bean(name = "getDataSources")
    public Map<Object,Object> getDataSources() throws PropertyVetoException {
        Map<Object,Object> targetDataSources = new HashMap<>();
            targetDataSources.put(DataSourceType.shenhuaDB.getType(),getShDataSource());
            targetDataSources.put(DataSourceType.taidaDB.getType(),getTdDataSource());
            targetDataSources.put(DataSourceType.guoanDB.getType(),getGaDataSource());
        return targetDataSources;
    }

    @Bean(name = "getShDataSource")
    @Primary
    public DataSource getShDataSource() throws PropertyVetoException {
        return createDataSource(shUrl,shDriveClass,shUser,shPass);
    }

    @Bean(name = "getTdDataSource")
    public DataSource getTdDataSource() throws PropertyVetoException {
        return createDataSource(tdUrl,tdDriveClass,tdUser,tdPass);
    }

    @Bean(name = "getGaDataSource")
    public DataSource getGaDataSource() throws PropertyVetoException {
        return createDataSource(gaUrl,gaDriverClass,gaUser,gaPass);
    }
}
