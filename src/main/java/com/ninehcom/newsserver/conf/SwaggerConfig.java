package com.ninehcom.newsserver.conf;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangbin on 2017/4/27.
 */
@Configuration
@EnableSwagger
@EnableAutoConfiguration
public class SwaggerConfig {

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    public SwaggerSpringMvcPlugin customImplementation(){
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*?");
    }

    private ApiInfo apiInfo(){
        ApiInfo apiInfo = new ApiInfo(
                "新闻服务",
                "新闻服务2",
                null,
                null,
                null,
                null
        );
        return apiInfo;
    }
}
