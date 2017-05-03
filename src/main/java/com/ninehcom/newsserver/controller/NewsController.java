package com.ninehcom.newsserver.controller;

import com.ninehcom.common.untils.Result;
import com.ninehcom.newsserver.service.NewsService;
import com.ninehcom.common.entity.PageRequest;
import com.wordnik.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * News 的控制器，用于显示同时查询2个数据库的结果 * @author shenjizhe
 *
 * @version 1.0.0
 */
@Api(basePath = "/newsserver",value = "新闻服务",description = "新闻服务",produces = "application/json")
@RestController
@RequestMapping(value = "/newsserver")
public class NewsController {

    @Autowired
    NewsService newsService;

    /**
     * 按照类型取得新闻列表
     *
     * @param typeId
     * @param page
     * @return
     */
    @ApiOperation(value = "按照类型取得新闻列表",notes = "按照类型取得新闻列表",position = 1)
    @RequestMapping(value = "/news/{news_type}", method = RequestMethod.GET)
    public Result selectNewsByType(
            @ApiParam(value = "新闻类型",required = false,name = "news_type")
            @PathVariable("news_type")
            int typeId,
            PageRequest page,
            @RequestHeader(value = "appId")
            String appId
    ) {
        return newsService.selectNewsByType(typeId, page);
    }

    /**
     * 按照ID记录阅读次数
     *
     * @param newsId 新闻ID
     */
    @ApiOperation(value = "按照ID记录阅读次数",notes = "按照ID记录阅读次数",position = 2)
    @RequestMapping(value = "/news/times/{news_id}", method = RequestMethod.POST)
    public Result updateNewsReadTimes(
            @ApiParam(value = "新闻id",required = false,name = "news_id")
            @PathVariable("news_id")
            int newsId,
            @RequestHeader(value = "appId")
            String appId
    ) {
        return newsService.updateNewsReadTimes(newsId);
    }
}
