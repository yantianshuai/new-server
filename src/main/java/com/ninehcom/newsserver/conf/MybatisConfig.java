package com.ninehcom.newsserver.conf;

import com.ninehcom.newsserver.mapper.EditconfigMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/2.
 */
@Configuration
@AutoConfigureAfter({ DataBaseConfig.class })
public class MybatisConfig {
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

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory SqlSessionFactory() {
        try{
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(routingDataSource());
            sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
            sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
            SqlSessionFactory sqlSessionFactory =null;
            try {
                sqlSessionFactory = sqlSessionFactoryBean.getObject();
            }catch (Exception e){
                e.printStackTrace();
                System.exit(0);
            }
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            configuration.setMapUnderscoreToCamelCase(true);
            return sqlSessionFactory;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Bean(name = "routingDataSource")
    public AbstractRoutingDataSource routingDataSource(){
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource();
        Map<Object, Object> targetDataSources = getDataSources;
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }




    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(){
        sqlSessionTemplate  = new SqlSessionTemplate(SqlSessionFactory());
        return sqlSessionTemplate;
    }


    @Bean
    public EditconfigMapper editconfigMapper(){
        return sqlSessionTemplate.getMapper(com.ninehcom.newsserver.mapper.EditconfigMapper.class);
    }




}
