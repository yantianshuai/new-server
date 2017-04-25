package com.ninehcom.newsserver.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangbin on 2017/4/24.
 * 获取配置文件中各个俱乐部appid的集合
 */
@Component
@ConfigurationProperties(prefix = "data_source_info")
public class AppIdConfig {

    private Map<String, String> app_id = new HashMap<>();

    public Map<String, String> getApp_id() {
        return app_id;
    }

    public void setApp_id(Map<String, String> app_id) {
        this.app_id = app_id;
    }
}
