package com.ninehcom.newsserver.service;

import com.ninehcom.common.enums.ConfigKeys;
import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.enums.MessageType;
import com.ninehcom.common.untils.Result;
import com.ninehcom.newsserver.agent.NewsCommentNotify;
import com.ninehcom.newsserver.entity.*;
import com.ninehcom.newsserver.exception.SensitiveException;
import com.ninehcom.newsserver.filter.SensitiveWordFilter;
import com.ninehcom.newsserver.mapper.EditconfigMapper;
import com.ninehcom.newsserver.mapper.NewsCommentMapper;
import com.ninehcom.newsserver.mapper.NewsMapper;
import com.ninehcom.newsserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;


/**
 * Created by zhangbin on 2016/9/22.
 * /news/{news-id}/comments
 */
@Service
public class NewsCommentService {

    @Autowired
    private NewsCommentMapper newsCommentMapper;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NewsCommentNotify notifyServiceAgent;
    @Autowired
    private SensitiveWordFilter sensitiveWordFilter;    //敏感词检测
    @Autowired
    private EditconfigMapper editconfigMapper;        //地址配置数据库获取


    private final Logger log = Logger.getLogger(NewsCommentService.class.getName());

    /**
     * 给新闻添加评论
     * @param newsId
     * @param userId
     * @param context
     * @param refId
     * @param refUserId
     * @return
     */
    public Result addNewsComment(Integer newsId, String userId, String context, Integer refId, String refUserId) {
        log.info("addNewsComment");
        //新闻id的合法性
        News news = newsMapper.selectNewsByID(newsId);
        if (null == news) {
            return Result.Fail(ErrorCode.NewsCommentTargetNewsIsNull);      //评论所属的新闻不存在
        }

        //检测传入的当前用户的userId的合法性
        User user = userMapper.selectUserByUid(userId);
        if (null == user) {
            return Result.Fail(ErrorCode.UidError);                         //传入的用户id不存在
        }

        //检测正文内容长度的合法性
        if (null == context) {
            return Result.Fail(ErrorCode.NewsCommentLengthError);           //评论内容最多为600字
        }
        if (null != context) {
            //内容的长度检测
            int len = context.length();
            if (1 > len || 600 < len) {                                     //这里需要补充条件 字数1-600个字
                return Result.Fail(ErrorCode.NewsCommentLengthError);       //评论内容最多为600字
            }
            //内容的敏感词检测
            try{
                String uurl = editconfigMapper.selectEditconfig(ConfigKeys.SensitiveWordURL.toString()).getValue();   //赋予敏感词库地址
                sensitiveWordFilter.setCheckUrl(uurl);
                sensitiveWordFilter.check(context);
            }catch (SensitiveException sensitiveException){
                Result reu = new Result();
                    reu.setErrCode(ErrorCode.NewsCommentContainSensitive.getCode());
                    reu.setMessage(sensitiveException.getMessage());
                return reu;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //如果有引用id传入，则检测引用评论的id合法性
        if (null != refId) {
            NewsComment oldNewsComment = newsCommentMapper.selectNewsCommentByCid(refId);   //通过原评论的评论id获取原评论
            if (null == oldNewsComment) {
                return Result.Fail(ErrorCode.NewsCommentTargetCommentIsNull);               //评论所属的新闻评论不存在
            }
            int oldNewsId = oldNewsComment.getNews_id();                                    //获取原评论所属新闻的新闻id
            int nId = newsId;
            if(oldNewsId != nId){
                return Result.Fail(ErrorCode.NewsComeentIsNotInNews);                       //传入的评论引用id与传入的新闻id不匹配
            }

            String authorId = oldNewsComment.getUser_id();                                  //获取原评论的 评论人id

            if (null == refUserId) {
                return Result.Fail(ErrorCode.NewsCommentAddUidNull);                          //被评论的原评论用户id不能为空
            }
            else{
                //如果有引用人id传入，则检测传入的引用人id是否和查找出来的相同
                if (null == authorId) {
                    return Result.Fail(ErrorCode.NewsCommentIsNotFullUidIsNull);            //原评论信息不完整，缺失评论人id
                }
                if (!refUserId.equals(authorId)) {                                          //检测传入的引用人id和查找出来的作者id相同
                    return Result.Fail(ErrorCode.NewsCommentUidError);                      //被评论的原评论用户id不正确
                }
            }
        }

        //插入评论 模块
        NewsComment newsComment = new NewsComment();
            newsComment.setNews_id(newsId);
            newsComment.setUser_id(userId);
            newsComment.setContext(context);
            newsComment.setRef_id(refId);
            newsComment.setRef_user_id(refUserId);

        Integer reback = newsCommentMapper.insertNewsComment(newsComment);
        if (1 != reback) {
            return Result.Fail(ErrorCode.NewsCommentAddError);              //新闻评论添加失败
        }
        Integer newsCommentId = newsComment.getId();                        //获取被插入评论的id

        //更新新闻表的评论数量字段
        int reNewCount = newsMapper.updateNewsCommentCount(newsId);

        // 通知
        if(null != newsCommentId && null != refUserId) {        //如果没有引用信息传入,则是评论新闻的评论,则不会发送通知,否则是评论评论的评论,要发送通知
            try {
                log.info("notify");
                Message message = new Message();
                message.setType(MessageType.COMMENT_ME_NEWS_COMMENT.toString());
                    message.setTargetId(Long.parseLong(Integer.valueOf(newsCommentId).toString()));
                    message.setContent(context);
                    message.setUserId(refUserId);
                    message.setFromUserId(userId);
                Result resul = notifyServiceAgent.postNotify(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Result.Success();
    }

    /**
     * 根据新闻id获取新闻下所有的评论对象
     * @param newsId
     * @param page_num
     * @param page_row
     * @return
     */
    public Result getNewsAllComments(Integer newsId , String userId , Integer page_num ,Integer page_row){

        int news_id = newsId;
        int count = 0;
        int offset = 0;
        if(null != page_num && null != page_row && page_num > 0 && page_row > 0){
            count = page_row;
            offset = (page_num-1)*page_row;
        }


        ArrayList<NewsComment> nlist = null;
        try {
            if(0 < count || 0 < offset) {     //如果有分页信息,执行分页操作
                nlist = (ArrayList<NewsComment>) newsCommentMapper.selectNewsCommentsByNid(news_id, count, offset);
            }else{                              //如果没有分页信息,则获取全部评论 不分页
                nlist = (ArrayList<NewsComment>) newsCommentMapper.selectAllNewsCommentsByNid(news_id);
            }
            for(int i=0 ; i<nlist.size() ; i++){
                NewsComment newsComment = nlist.get(i);

                User user = userMapper.selectUserByUid(newsComment.getUser_id());
                    List<Tag> tags0 = userMapper.selectTagByUserId(newsComment.getUser_id());
                    if (null != tags0) {
                        user.setTagList(tags0);
                    }
                if(null != user) {                                                          //根据传入的user-id查询该用户的昵称和头像信息，准备装配到评论对象中去
                    newsComment.setAuthor(user);
                }else{
                    return Result.Fail(ErrorCode.UidError);
                }

                if(!userId.equals("null")) {                                                                                           //如果以游客身份登录，userId为空不会查询点赞关系
                    Integer commentId = newsComment.getId();
                    NewsCommentPraise newsCommentPraise = newsCommentMapper.selectNewsCommentLoveLogByCidUid(commentId, userId);       //根据评论id和用户id来获取点赞关系的对象

                    Integer commentPraiseId = null;
                    if (null != newsCommentPraise) {
                        commentPraiseId = newsCommentPraise.getId();                                                                    //获取 赞对象的id 赋值到新闻评论对象中
                        newsComment.setPraise_id(commentPraiseId);
                    }
                }
                Integer refId0 = newsComment.getRef_id();                                                                           //如果评论是二级评论，搜出来返出去
                if(null != refId0){
                    NewsComment refComm = newsCommentMapper.selectNewsCommentByCid(refId0);
                    String uuid = refComm.getUser_id();
                    if(null != uuid) {
                        refComm.setAuthor(userMapper.selectUserByUid(uuid));
                    }
                    newsComment.setRef_news_comment(refComm);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int newsCount = newsCommentMapper.selectNewsCommentsNumByNid(newsId);


        HashMap<String , Object> map0 = new HashMap<>();
            map0.put("newsCommentList",nlist);
            map0.put("newsCommentCount",newsCount);
        return Result.Success(map0);
    }

    /**
     * 根据新闻id 获取新闻下固定3条的热门评论（点赞数>20）
     * @param newsId
     * @return
     */
    public Result getHotNewsCommentsByNid(Integer newsId ,String userId){
        ArrayList<NewsComment>  nlist  = (ArrayList<NewsComment>) newsCommentMapper.selectHotNewsCommentsByNid(newsId);
        for(int i=0 ; i<nlist.size() ; i++){
            NewsComment newsComment = nlist.get(i);

            User user = userMapper.selectUserByUid(newsComment.getUser_id());                                                      //查询评论的用户昵称
            List<Tag> tags0 = userMapper.selectTagByUserId(newsComment.getUser_id());
            if (null != tags0) {
                user.setTagList(tags0);
            }
            newsComment.setAuthor(user);

            if(!userId.equals("null")) {                                                                                                   //如果以游客身份登录，userId为空不会查询点赞关系
                Integer commentId = newsComment.getId();
                NewsCommentPraise newsCommentPraise = newsCommentMapper.selectNewsCommentLoveLogByCidUid(commentId, userId);       //根据评论id和用户id来获取点赞关系的对象
                Integer commentPraiseId = null;
                if (null != newsCommentPraise) {
                    commentPraiseId = newsCommentPraise.getId();                                                                   //获取 赞对象的id 赋值到新闻评论对象中
                    newsComment.setPraise_id(commentPraiseId);
                }
            }

            Integer refId0 = newsComment.getRef_id();                                                                           //如果评论是二级评论，搜出来返出去
            if(null != refId0){
                NewsComment refComm = newsCommentMapper.selectNewsCommentByCid(refId0);
                String uuid = refComm.getUser_id();
                if(null != uuid) {
                    refComm.setAuthor(userMapper.selectUserByUid(uuid));
                }
                newsComment.setRef_news_comment(refComm);
            }
        }

        int newsCount = newsCommentMapper.selectNewsCommentsNumByNid(newsId);                                                   //查询当前新闻全部评论的个数

        HashMap<String , Object> map0 = new HashMap<>();
            map0.put("newsCommentList",nlist);
            map0.put("newsCommentCount",newsCount);
        return Result.Success(map0);
    }

    /**
     * 点赞或者取消点赞
     * @param news_id
     * @param comment_id
     * @param praise_id
     * @param user_id
     * @return
     */
    public Result praiseNewsComment(Integer news_id,Integer comment_id,Integer praise_id,String user_id){

        if(null == user_id){
            return Result.Fail(ErrorCode.NewsCommentPraiseUserIdNull);                      //此处的用户id不能为空
        }
        if(null == comment_id){
            return Result.Fail(ErrorCode.NewsCommentIdNull);                                //此处的评论id不能为空
        }
        NewsCommentPraise newsCommentPraise0 = newsCommentMapper.selectNewsCommentLoveLogByCidUid(comment_id,user_id);

        if(null == praise_id){
            //点赞区
            NewsComment newsComment0 = newsCommentMapper.selectNewsCommentByCid(comment_id);
            if(null == newsComment0){
                return Result.Fail(ErrorCode.NewsCommentIsNull);                            //目标评论不存在
            }
            int nid0 = newsComment0.getNews_id();                                           //来自数据库的评论所属新闻的id
            int nid = news_id;                                                              //传入的新闻id
            if(nid0 != nid){
                return Result.Fail(ErrorCode.NewsCommentNewIdError);                        //传入的评论id和新闻id不匹配
            }

            String comUser = newsComment0.getUser_id();
            //安全性：检测是否已经点过了
            if(null != newsCommentPraise0){
                return Result.Fail(ErrorCode.NewsCommentIsPraised);                         //您已经赞过啦！
            }

            NewsCommentPraise newsCommentPraise = new NewsCommentPraise();
                newsCommentPraise.setUser_id(user_id);              //当前用户的nhid
                newsCommentPraise.setNews_id(news_id);              //被赞评论所属的新闻的id
                newsCommentPraise.setRef_id(comment_id);            //被赞的评论id
                newsCommentPraise.setRef_user_id(comUser);          //给谁点的赞
            int reCom = newsCommentMapper.insertNewsCommentLoveLog(newsCommentPraise);      //插入点赞记录

            int reInt = newsCommentMapper.updateNewsCommentPraiseAdd(comment_id);           //评论列表点赞数增加 1
            return Result.Success(newsCommentPraise);
        }else {
            //取消点赞区
            if(null == newsCommentPraise0){
                return Result.Fail(ErrorCode.NewsCommentNotPraised);                        //取消点赞失败,您之前没有点过赞
            }
            int cid = newsCommentPraise0.getId();
            if(cid!=praise_id){
                return Result.Fail(ErrorCode.NewsCommentPraiseIdError);                     //取消点赞失败，相应的赞记录与传入的praise_id不匹配
            }
            int reDeIn = newsCommentMapper.deleteNewsCommentLoveLog(praise_id);             //删除点赞记录
            int reInt = newsCommentMapper.updateNewsCommentPraiseCancel(comment_id);        //评论列表更新点赞数
            return Result.Success();
        }
    }


    public Result autoPraiseNewsComment(Integer news_id,Integer comment_id,String user_id , Integer praise_count){
        ArrayList<User> users = (ArrayList<User>) userMapper.selectUserIdByCount(0,praise_count);
        for (int i = 0 ; i<users.size() ; i++){
            String uid = users.get(i).getUserId();

            String comUser = userMapper.selectUserByUid(newsCommentMapper.selectNewsCommentByCid(comment_id).getUser_id()).getUserId();

            Result resul = praiseNewsComment(news_id,comment_id,null,uid);         //点赞方法
            if(resul.getErrCode() != 0 ){
                return resul;
            }
        }
        return Result.Success(users.size());
    }

    /**
     * 获取指定的评论
     * @param comment_id
     * @return
     */
    public Result getNewsComment(Integer comment_id){
        NewsComment newsComment = newsCommentMapper.selectNewsCommentByCid(comment_id);
        return Result.Success(newsComment);
    }

}
