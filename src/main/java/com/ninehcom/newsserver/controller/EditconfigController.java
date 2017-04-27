package com.ninehcom.newsserver.controller;

import com.ninehcom.common.untils.Result;
import com.ninehcom.newsserver.entity.Editconfig;
import com.ninehcom.newsserver.service.EditconfigService;
import com.wordnik.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/newsserver")
public class EditconfigController {

    @Autowired
    EditconfigService editconfigService;

    @ApiOperation(value = "根据配置键获取指定的配置",notes = "根据配置键获取指定的配置",position = 1)
    @RequestMapping(value = "/config/{key}", method = RequestMethod.GET)
    @ResponseBody
    public Result getConfig(
            @ApiParam(value = "配置键", defaultValue = "")
            @PathVariable("key") String key,
            @RequestHeader(value = "appId") String appId) {
        return editconfigService.getValueById(key);
    }

    @ApiOperation(value = "获取全部的配置项",notes = "获取全部的配置项",position = 2)
    @RequestMapping(value = "/configs", method = RequestMethod.GET)
    @ResponseBody
    public Result getConfig(
            @RequestHeader(value = "appId") String appId) {
        return editconfigService.selectClientEditconfig();
    }

}
