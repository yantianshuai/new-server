package com.ninehcom.newsserver.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Created by zhangbin on 2017/4/20.
 */

@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
@MapperScan(basePackages = {"com.ninehcom.newsserver"})
@EnableTransactionManagement(proxyTargetClass=true)
public class MybatisConfiguration{
    private static final Logger log = Logger.getLogger(MybatisConfiguration.class.getName());

    @Value("${mybatis.typeAliasesPackage}")
    protected String typeAliasesPackage;
    @Value("${mybatis.mapperLocations}")
    protected String mapperLocations;
    @Value("${mybatis.configLocation}")
    protected String configLocation;

    @Resource
    public Map<Object,Object> getDataSources;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(){
        SqlSessionFactory sqlSessionFactory = null;
        try {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(routingDataSource());
            factoryBean.setTypeAliasesPackage(typeAliasesPackage);                                                          //对应实体类所在的包,自动获取短名（不包括包名）
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
            factoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));

            try {
                sqlSessionFactory = factoryBean.getObject();
            }catch (Exception e){
                e.printStackTrace();
                System.exit(0);
            }
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

