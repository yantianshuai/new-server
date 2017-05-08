package com.ninehcom.newsserver.mapper;


import com.ninehcom.newsserver.entity.NewsComment;
import com.ninehcom.newsserver.entity.NewsCommentPraise;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangbin on 2016/9/20.
 * 新闻评论 的mapper
 */
@Repository
public class NewsCommentMapper extends BaseMapper{

    public int insertNewsComment(NewsComment newsComment){
        return sqlSession.insert("insertNewsComment",newsComment);
    }

    //点赞后增加点赞数
    public int updateNewsCommentPraiseAdd(int newsCommentId){
        return sqlSession.update("updateNewsCommentPraiseAdd",newsCommentId);
    }

    //取消点赞后渐趋点赞数量
    public int updateNewsCommentPraiseCancel(int newsCommentId){
        return sqlSession.update("updateNewsCommentPraiseCancel",newsCommentId);
    }



    //根据 新闻id 获取新闻下所有的评论对象(带分页)
    public List<NewsComment> selectNewsCommentsByNid(int news_id, int count, int offset){
        Map<String,Integer> map = new HashMap<>();
            map.put("news_id",news_id);
            map.put("count",count);
            map.put("offset",offset);
        return sqlSession.selectList("selectNewsCommentsByNid",map);
    }
    //根据 新闻id 获取新闻下所有的评论对象(不带分页功能)
    public List<NewsComment> selectAllNewsCommentsByNid(int news_id){
        return sqlSession.selectList("selectAllNewsCommentsByNid",news_id);
    }
    //根据 新闻id 获取新闻下评论集合的数量
    public int selectNewsCommentsNumByNid(int news_id){
        return sqlSession.selectOne("selectNewsCommentsNumByNid",news_id);
    }
    //根据评论id获取评论对象
    public NewsComment selectNewsCommentByCid(int newsCommentId){
        return sqlSession.selectOne("selectNewsCommentByCid",newsCommentId);
    }

    //根据新闻id 获取新闻下固定10条的热门评论（点赞数>20）
    public List<NewsComment> selectHotNewsCommentsByNid(Integer news_id){
        return sqlSession.selectList("selectHotNewsCommentsByNid",news_id);
    }



    //操作点赞记录表
    //插入点赞记录
    public int insertNewsCommentLoveLog(NewsCommentPraise newsCommentPraise){
        return sqlSession.insert("insertNewsCommentLoveLog",newsCommentPraise);
    }
    //删除点赞记录
    public int deleteNewsCommentLoveLog(int praiseId){
        return sqlSession.delete("deleteNewsCommentLoveLog",praiseId);
    }
    //根据被赞的评论id 和 当前用户的nhid 查询点赞记录
    public NewsCommentPraise selectNewsCommentLoveLogByCidUid(int comment_id, String user_id){
        Map<String,Object> map = new HashMap<>();
            map.put("comment_id",comment_id);
            map.put("user_id",user_id);
        return sqlSession.selectOne("selectNewsCommentLoveLogByCidUid",map);
    }
}
