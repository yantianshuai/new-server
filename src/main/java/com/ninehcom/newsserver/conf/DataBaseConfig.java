package com.ninehcom.newsserver.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/2.
 */
@Configuration
@EnableTransactionManagement
public class DataBaseConfig {
    @Value("${guoan_test.url}")
    private String gaUrl;
    @Value("${sh_test.url}")
    private String shUrl;
    @Value("${td_test.url}")
    private String tdUrl;


    @Value("${guoan_test.driverClass}")
    private String gaDriverClass;
    @Value("${sh_test.driverClass}")
    private String shDriveClass;
    @Value("${td_test.driverClass}")
    private String tdDriveClass;


    @Value("${guoan_test.user}")
    private String gaUser;
    @Value("${sh_test.user}")
    private String shUser;
    @Value("${td_test.user}")
    private String tdUser;


    @Value("${guoan_test.password}")
    private String gaPass;
    @Value("${sh_test.password}")
    private String shPass;
    @Value("${td_test.password}")
    private String tdPass;

    @Bean(name = "getDataSources")
    public Map<Object,Object> getDataSources() throws PropertyVetoException, IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
//        Properties properties = new Properties();
//        properties.load(DataBaseConfig.class.getClassLoader().getResourceAsStream("appid-clubid.properties"));
//        Set<Object> set = properties.keySet();
//        DataBaseConfig dataBaseConfig = DataBaseConfig.class.newInstance();
//        for (Object obj:set
//                ) {
//            Method method = dataBaseConfig.getClass().getDeclaredMethod(String.valueOf(properties.get(obj)));
//            DataSource d = (DataSource) method.invoke(dataBaseConfig);
//            targetDataSources.put(properties.get(obj),d);
//        }
        targetDataSources.put(DataSourceType.gaDataSource.getType(),gaDataSource());
        targetDataSources.put(DataSourceType.tdDataSource.getType(),tdDataSource());
        targetDataSources.put(DataSourceType.shDataSource.getType(),shDataSource());
        return targetDataSources;
    }


    @Bean(name = "gaDataSource")
    @Primary
    public DataSource gaDataSource() throws PropertyVetoException {
        return create(gaUrl,gaDriverClass,gaUser,gaPass);
    }
    @Bean(name = "shDataSource")
    public DataSource shDataSource() throws PropertyVetoException {
        return create(shUrl,shDriveClass,shUser,shPass);
    }
    @Bean(name = "tdDataSource")
    public DataSource tdDataSource() throws PropertyVetoException {
        return create(tdUrl,tdDriveClass,tdUser,tdPass);
    }
    public DataSource create(String url,String driveClass,String user,String pass) throws PropertyVetoException {
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
}
