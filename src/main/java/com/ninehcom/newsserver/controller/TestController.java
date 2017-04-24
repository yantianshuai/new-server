package com.ninehcom.userinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by shixiaoqi on 2017/4/17.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test-aop",method = RequestMethod.GET)
    public String test(@RequestParam(value = "appId") String appId){
        System.out.println(appId+"........");
        return appId;
    }
}
