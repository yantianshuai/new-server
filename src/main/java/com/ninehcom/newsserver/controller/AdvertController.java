package com.ninehcom.newsserver.controller;

import com.ninehcom.common.untils.Result;
import com.ninehcom.newsserver.entity.Advert;
import com.ninehcom.newsserver.service.AdvertService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import com.ninehcom.newsserver.jpa.repository.AdvertRepository;

/**
 * Advert 的控制器，用于显示同时查询2个数据库的结果 * @author shenjizhe
 * @version 1.0.0
 */
@Api(basePath = "/newsserver",value = "新闻服务",description = "新闻服务",produces = "application/json")
@RestController
@RequestMapping(value = "/newsserver")
public class AdvertController {

    @Autowired
    AdvertService advertService;

    @ApiOperation(value="查询所有的广告",notes = "查询所有的广告",position = 1)
    @RequestMapping(value = "/advert", method = RequestMethod.GET)
    @ResponseBody
    public Result selectAllAdvert(
            @RequestHeader(value = "appId",required = true)
            String appId
    ) {
        return advertService.selectAllAdvert();
    }
}
