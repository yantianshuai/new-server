package com.ninehcom.newsserver.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Created by zhangbin on 2017/4/20.
 */

@Configuration
@MapperScan(basePackages = {"com.newsserver"})
@AutoConfigureAfter({DataSourceConfiguration.class})
public class MybatisConfiguration implements EnvironmentAware{

    private static final Logger log = Logger.getLogger(MybatisConfiguration.class.getName());

    private RelaxedPropertyResolver propertyResolver;

    @Resource
    public Map<Object,Object> getDataSources;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"mybatis.");
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(){
        SqlSessionFactory sqlSessionFactory = null;
        try {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(routingDataSource());
            factoryBean.setTypeAliasesPackage(propertyResolver.getProperty("typeAliasesPackage"));                                                          //对应实体类所在的包,自动获取短名（不包括包名）
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(propertyResolver.getProperty("mapperLocations")));
            factoryBean.setConfigLocation(new DefaultResourceLoader().getResource(propertyResolver.getProperty("configLocation")));
            sqlSessionFactory = factoryBean.getObject();
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            configuration.setMapUnderscoreToCamelCase(true);
        } catch (Exception e) {
            return null;
        }
        return sqlSessionFactory;
    }

    @Bean(name = "routingDataSource")
    public AbstractRoutingDataSource routingDataSource(){
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource();
        Map<Object,Object> targetDataSources = getDataSources;
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

    @Bean(name="sqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(){
        sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate;
    }
}

