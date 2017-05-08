/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.newsserver.agent;


import com.ninehcom.common.enums.ConfigKeys;
import com.ninehcom.common.untils.Result;
import com.ninehcom.newsserver.entity.Message;
import com.ninehcom.newsserver.service.EditconfigService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;


/**
 *  系统通知服务代理对象
 * @author Administrator
 */
@Component
public class NewsCommentNotify {

    private static String notifyUrl = "";

    @Autowired
    ConnectAgent agent;

    @Autowired
    private EditconfigService configService;
    public NewsCommentNotify(){

    }

//    @PostConstruct
//    private void init() {
//        notifyUrl = configService.getValueById(ConfigKeys.NewsCommentNotify);
//    }

    //发送系统通知
    public Result postNotify(Message message){
        notifyUrl = configService.getValueById(ConfigKeys.NewsCommentNotify);
        String request = notifyUrl+"/group/v1/message/new";
        JSONObject json = JSONObject.fromObject(message);
        String jsonString = json.toString();

        String backInfo = null;
        try {
            backInfo = agent.sendRequestHttpsUTF8Post(request,jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Result result = Result.Success();
        if(null != backInfo && backInfo.length()>0){
            result = StringToResult(backInfo);
        }

        return result;
    }

    //String转换成result对象
    public Result StringToResult(String jsonSting){
        JSONObject jsonO = new JSONObject().fromObject(jsonSting);

        int errCode = Integer.parseInt(jsonO.get("errCode").toString());
        String msg = jsonO.get("message").toString();

        Result result  = new Result();
            result.setErrCode(errCode);
            result.setMessage(msg);
        return result;
    }

}
