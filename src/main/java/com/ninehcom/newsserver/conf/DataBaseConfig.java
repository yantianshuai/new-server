package com.ninehcom.newsserver.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ninehcom.common.enums.DataSourceType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangbin on 2017/4/25.
 * 配置数据库信息；
 */
@Component
@ConfigurationProperties(prefix = "data_source_info")
public class DataBaseConfig {

    private Map<String,String> guoan = new HashMap<>();

    private Map<String,String> taida = new HashMap<>();

    private Map<String,String> shenhua = new HashMap<>();


    @Bean(name = "getDataSources")
    public Map<Object,Object> getDataSources() throws PropertyVetoException, IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        targetDataSources.put(DataSourceType.gaDataSource.getType(),gaDataSource());
        targetDataSources.put(DataSourceType.tdDataSource.getType(),tdDataSource());
        targetDataSources.put(DataSourceType.shDataSource.getType(),shDataSource());
        return targetDataSources;
    }


    @Bean(name = "gaDataSource")
    @Primary
    public DataSource gaDataSource() throws PropertyVetoException {
        Map<String,String> guoanDB = getGuoan();
        return createDataSource(
                guoanDB.get("url"),
                guoanDB.get("driverClass"),
                guoanDB.get("user"),
                guoanDB.get("password")
        );
    }
    @Bean(name = "shDataSource")
    public DataSource shDataSource() throws PropertyVetoException {
        Map<String,String> shenhuaDB = getShenhua();
        return createDataSource(
                shenhuaDB.get("url"),
                shenhuaDB.get("driverClass"),
                shenhuaDB.get("user"),
                shenhuaDB.get("password")
        );
    }
    @Bean(name = "tdDataSource")
    public DataSource tdDataSource() throws PropertyVetoException {
        Map<String,String> taidaDB = getTaida();
        return createDataSource(
                taidaDB.get("url"),
                taidaDB.get("driverClass"),
                taidaDB.get("user"),
                taidaDB.get("password")
        );
    }
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

    public Map<String, String> getGuoan() {
        return guoan;
    }

    public void setGuoan(Map<String, String> guoan) {
        this.guoan = guoan;
    }

    public Map<String, String> getTaida() {
        return taida;
    }

    public void setTaida(Map<String, String> taida) {
        this.taida = taida;
    }

    public Map<String, String> getShenhua() {
        return shenhua;
    }

    public void setShenhua(Map<String, String> shenhua) {
        this.shenhua = shenhua;
    }
}
