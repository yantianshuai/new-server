//package com.ninehcom.newsserver.conf;
//
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.bind.RelaxedPropertyResolver;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import javax.sql.DataSource;
//import java.util.Map;
//import java.util.Properties;
//import java.util.logging.Logger;
//
///**
// * Created by zhangbin on 2017/4/21.
// */
//
//@Configuration
//public class AppidConfig {
//
//    private static final Logger log = Logger.getLogger(AppidConfig.class.getName());
//
//    private Properties properties;
//
//    public Map<String,String> getAppids(){
//        this.properties = new Properties();
//        properties.load(AppidConfig.class.getClassLoader().getResourceAsStream());
//    }
//}
