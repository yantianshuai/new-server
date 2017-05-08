package com.ninehcom.newsserver.controller;


import com.ninehcom.common.enums.DataSourceType;
import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.untils.Result;
import com.ninehcom.newsserver.conf.DataSourceContextHolder;
import com.ninehcom.newsserver.entity.RequestAddNewsComment;
import com.ninehcom.newsserver.entity.RequestNewsCommentPraiseBody;
import com.ninehcom.newsserver.service.NewsCommentService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhangbin on 2016/9/22.
 */
;

@Api(basePath = "/newsserver",value = "新闻评论",description = "新闻评论",produces = "application/json")
@RestController
@RequestMapping(value = "/newsserver")
public class NewsCommentController {

    @Autowired
    private NewsCommentService newsCommentService;

    ///news/{news-id}/comments
    @ApiOperation(value = "给新闻添加评论", notes = "给新闻添加评论", position = 1)
    @RequestMapping(value = "/news/{news_id}/comments", method = RequestMethod.POST)
    public Result addNewsComment(
            @ApiParam(value = "news_id", required = true, name = "news_id")
            @PathVariable("news_id") Integer newsId,
            @RequestBody RequestAddNewsComment requestAddNewsComment,
            @ApiParam(value = "特定的appId",required = false,name = "appId")
            @RequestHeader(value = "appId")
            String appId
    ) {
        //判断当前俱乐部是否开通了新闻评论的相关功能
        if(!checkIsCanComment()){
            return Result.Fail(ErrorCode.NewsCommentIsClose);    //当前新闻评论功能未开通
        }
        Result reBack = newsCommentService.addNewsComment(newsId, requestAddNewsComment.getUser_id(), requestAddNewsComment.getContext(), requestAddNewsComment.getRef_id(), requestAddNewsComment.getRef_user_id());
        return reBack;
    }


    @ApiOperation(value = "获取新闻下的所有评论(带分页)/获取新闻下的热门评论", notes = "获取新闻下的所有评论(带分页) or 获取新闻下的热门评论", position = 3)
    @RequestMapping(value = "/news/{news_id}/comments", method = RequestMethod.GET)
    public Result addNewsComment(
            @ApiParam(value = "news_id", required = true, name = "news_id")
            @PathVariable("news_id") Integer news_id,
            @ApiParam(value = "user-id", required = false, name = "user-id")
            @RequestParam(value = "user-id" , required = false ,defaultValue = "null") String userId,
            @ApiParam(value = "page-num", required = false, name = "page-num")
            @RequestParam(value = "page-num", required = false, name = "page-num", defaultValue = "0") Integer page_num,
            @ApiParam(value = "page-row", required = false, name = "page-row")
            @RequestParam(value = "page-row", required = false, name = "page-row", defaultValue = "0") Integer page_row,
            @ApiParam(value = "isHot", required = false, name = "isHot")
            @RequestParam(value = "isHot", required = false, name = "isHot", defaultValue = "false") String isHot,
            @ApiParam(value = "特定的appId",required = false,name = "appId")
            @RequestHeader(value = "appId")
            String appId
    ) {
        //判断当前俱乐部是否开通了新闻评论的相关功能
        if(!checkIsCanComment()){
            return Result.Fail(ErrorCode.NewsCommentIsClose);    //当前新闻评论功能未开通
        }
        Result reBack = null;
        if ("true".equals(isHot)) {      //如果获取到了isHot的值,则走获取 热门评论 逻辑,只返3条
            reBack = newsCommentService.getHotNewsCommentsByNid(news_id, userId);
        } else {                          //如果没有获取到isHot的值,则走获取 全部评论 的逻辑,带分页
            reBack = newsCommentService.getNewsAllComments(news_id, userId, page_num, page_row);
        }
        return reBack;
    }


    @ApiOperation(value = "新闻评论点赞", notes = "新闻评论点赞", position = 4)
    @RequestMapping(value = "/news/{news_id}/comments/{comment_id}/praise", method = RequestMethod.POST)
    public Result praiseNewsComment(
            @ApiParam(value = "news_id", required = true, name = "news_id")
            @PathVariable("news_id") Integer news_id,
            @ApiParam(value = "comment_id", required = true, name = "comment_id")
            @PathVariable("comment_id") Integer comment_id,
            @RequestBody RequestNewsCommentPraiseBody requestNewsCommentPraiseBody,
            @ApiParam(value = "特定的appId",required = false,name = "appId")
            @RequestHeader(value = "appId")
            String appId
    ) {
        //判断当前俱乐部是否开通了新闻评论的相关功能
        if(!checkIsCanComment()){
            return Result.Fail(ErrorCode.NewsCommentIsClose);    //当前新闻评论功能未开通
        }
        Result reBack = newsCommentService.praiseNewsComment(news_id, comment_id, null, requestNewsCommentPraiseBody.getUser_id());
        return reBack;
    }

    @ApiOperation(value = "新闻评论取消点赞", notes = "新闻评论取消点赞", position = 5)
    @RequestMapping(value = "/news/{news_id}/comments/{comment_id}/praise/{praise_id}", method = RequestMethod.DELETE)
    public Result praiseNewsComment(
            @ApiParam(value = "news_id", required = true, name = "news_id")
            @PathVariable("news_id") Integer news_id,
            @ApiParam(value = "comment_id", required = true, name = "comment_id")
            @PathVariable("comment_id") Integer comment_id,
            @ApiParam(value = "praise_id", required = true, name = "praise_id")
            @PathVariable("praise_id") Integer praise_id,
            @ApiParam(value = "user-id", required = true, name = "user-id")
            @RequestHeader(value = "user-id", required = true, name = "user-id") String user_id,
            @ApiParam(value = "特定的appId",required = false,name = "appId")
            @RequestHeader(value = "appId")
            String appId
    ) {
        //判断当前俱乐部是否开通了新闻评论的相关功能
        if(!checkIsCanComment()){
            return Result.Fail(ErrorCode.NewsCommentIsClose);    //当前新闻评论功能未开通
        }
        Result reBack = newsCommentService.praiseNewsComment(news_id, comment_id, praise_id, user_id);
        return reBack;
    }


    /**
     * 判断当前俱乐部是否开通了新闻评论的相关功能，没开通新闻评论的俱乐部请求，返回失败
     * @return
     */
    public Boolean checkIsCanComment(){
        //如果是非申花的请求，返回评论不存在
        String currentDataSource = DataSourceContextHolder.getJdbcType();
        if(!currentDataSource.equals(DataSourceType.shDataSource.getType())){
            return false;
        }else{
            return true;
        }

    }

//    @ApiOperation(value = "测试填数据：自动指定数量的点赞", notes = "自动指定数量的点赞", position = 5)
//    @RequestMapping(value = "/news/{news_id}/comments/{comment_id}/praise", method = RequestMethod.GET)
//    public Result autoPraiseNewsComment(
//            @ApiParam(value = "news_id", required = true, name = "news_id")
//            @PathVariable("news_id") Integer news_id,
//            @ApiParam(value = "comment_id", required = true, name = "comment_id")
//            @PathVariable("comment_id") Integer comment_id,
//            @ApiParam(value = "praise-count", required = false, name = "praise-count")
//            @RequestParam("praise-count") Integer praise_num
//    ) {
//        Result res = newsCommentService.autoPraiseNewsComment(news_id,comment_id,null,praise_num);
//        return res;
//    }

}
