package com.ninehcom.newsserver.controller;

import com.ninehcom.common.untils.Result;
import com.ninehcom.newsserver.entity.Roll;
import com.ninehcom.newsserver.service.RollService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Roll 的控制器，用于显示同时查询2个数据库的结果 * @author shenjizhe
 * @version 1.0.0
 */
@Api(basePath = "/newsserver",value = "新闻服务",description = "新闻服务",produces = "application/json")
@RestController
@RequestMapping(value = "/newsserver")
public class RollController {

    @Autowired
    RollService rollService;

    @ApiOperation(value = "查询所有的roll",notes = "查询所有的roll",position = 1)
    @RequestMapping(value = "/roll/{count}", method = RequestMethod.GET)
    @ResponseBody
    public Result selectAllRoll(
            @ApiParam(value = "count", required = true,name = "count")
            @PathVariable("count")int count,
            @RequestHeader(value = "appId") String appId
    ) {
        return rollService.selectAllRoll(count);
    }
}
