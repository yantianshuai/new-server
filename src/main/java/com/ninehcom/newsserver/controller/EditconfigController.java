package com.ninehcom.newsserver.controller;

import com.ninehcom.newsserver.entity.Editconfig;
import com.ninehcom.newsserver.service.EditconfigService;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Editconfig 的控制器，用于显示同时查询2个数据库的结果 * @author shenjizhe
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/users")
public class EditconfigController {

    @Autowired
    EditconfigService editconfigService;

    @RequestMapping(value = "/config/{key}", method = RequestMethod.GET)
    @ResponseBody
    public String getConfig(
            @ApiParam(value = "配置键", defaultValue = "")
            @PathVariable("key") String key,
            @RequestHeader(value = "appId") String appId) {
        return editconfigService.getConfigValue(key);
    }

    @RequestMapping(value = "/configs", method = RequestMethod.GET)
    @ResponseBody
    public List<Editconfig> getConfig(
            @RequestHeader(value = "appId") String appId) {
        return editconfigService.getValues();
    }

}
