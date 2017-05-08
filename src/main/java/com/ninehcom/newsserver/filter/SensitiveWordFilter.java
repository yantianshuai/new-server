package com.ninehcom.newsserver.filter;


import com.ninehcom.common.untils.HttpUtil;
import com.ninehcom.common.untils.StringUtil;
import com.ninehcom.newsserver.exception.SensitiveException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Administrator on 2016/9/26.
 */
@Component
@EnableConfigurationProperties(SensitiveWordFilter.class)
@ConfigurationProperties(prefix = "sensitive")
public class SensitiveWordFilter {
    private static Logger log = LoggerFactory.getLogger(SensitiveWordFilter.class);

    private String checkUrl;

    public String getCheckUrl() {
        return checkUrl;
    }

    public void setCheckUrl(String checkUrl) {
        this.checkUrl = checkUrl;
    }


    private JSONObject checkWork(String word) throws IOException,JSONException{
        String jsonResultStr = HttpUtil.postAsString(checkUrl,word,"utf-8");
        JSONObject jsonResult = new JSONObject(jsonResultStr);
        return jsonResult;
    }

    public void check(String word) throws JSONException,IOException,SensitiveException {
        if (StringUtil.isEmpty(word)){
            return ;
        }
        JSONObject jsonObject = this.checkWork(word);
        if(jsonObject.getBoolean("contain")){
            String message = "含有敏感词:"+jsonObject.getJSONArray("keywords");
            throw new SensitiveException(message);
        }
    }
}
