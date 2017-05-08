package com.ninehcom.newsserver.mapper;

import com.ninehcom.newsserver.entity.Tag;
import com.ninehcom.newsserver.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/22.
 */
@Repository
public class UserMapper extends BaseMapper{

    public User selectUserByUid(String uid){
        return sqlSession.selectOne("selectUserByUid",uid);
    }

    public List<Tag> selectTagByUserId(String userId){
        return sqlSession.selectList("selectTagByUserId",userId);
    }

    public List<User> selectUserIdByCount(Integer pageNum, Integer pageRow){
        Map<String,Integer> map = new HashMap<>();
            map.put("pageNum",pageNum);
            map.put("pageRow",pageRow);
        return sqlSession.selectList("selectUserIdByCount",map);
    }
}
