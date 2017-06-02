package com.ninehcom.newsserver.mapper;

import com.ninehcom.newsserver.entity.News;
import com.ninehcom.common.entity.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * News的Mapper，用于Mybatis
 *
 * @author shenjizhe
 * @version 1.0.0
 */
@Repository
public class NewsMapper extends BaseMapper{

    public List<News> selectNewsByType(int typeId, PageRequest page){
        Map<String, Object> map = new HashMap<>();
        if(typeId != 0){
            map.put("typeId",String.valueOf(typeId));
        }
        if(null != page){
            map.put("direction",page.getDirection().toString());
            map.put("needPage", String.valueOf(page.isNeedPage()));
            map.put("offset",page.getOffset());
            map.put("pageSize",page.getPageSize());
        }
        return sqlSession.selectList("selectNewsByType",map);
    }
    
    public News selectNewsByID(int id){
        return sqlSession.selectOne("selectNewsByID",id);
    }

    public int selectNewsCountByType(int typeId){
        return sqlSession.selectOne("selectNewsCountByType",typeId);
    }

    public int updateNewsReadTimes(int newsId, int count){
        Map<String,Integer> map = new HashMap<>();
            map.put("newsId",newsId);
            map.put("count",count);
        return sqlSession.update("updateNewsReadTimes",map);
    }

    public int updateNewsCommentCount(int newsId){
        return sqlSession.update("updateNewsCommentCount",newsId);
    }
}
