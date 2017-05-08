/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.newsserver.agent;

import com.ninehcom.common.untils.HttpUtil;
import com.ninehcom.common.untils.HttpsUtil;
import org.springframework.stereotype.Component;

/**
 *
 * @author Shenjizhe
 */
@Component
public class ConnectAgent {
    
    public String sendRequestHttpsUTF8Post(String request,String content) throws Exception{
        String httpHead = request.substring(0,5);
        if("https".equals(httpHead)){
            return null;
        }else{
            return HttpUtil.postAsString(request, content, "utf-8");
        }
    }
    public String sendRequestHttpsUTF8Get(String request) throws Exception{
        String httpHead = request.substring(0,5);
        if("https".equals(httpHead)){
            return HttpsUtil.getAsString(request, "utf-8");
        }else{
            return HttpUtil.getAsString(request, "utf-8", null);
        }
    }

}
