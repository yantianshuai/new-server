package com.ninehcom.newsserver.mapper;

import com.ninehcom.newsserver.entity.Roll;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Roll的Mapper，用于Mybatis
 *
 * @author zhangbin
 * @version 1.0.0
 */
@Repository
public class RollMapper extends BaseMapper{

    public List<Roll> selectAllRoll(int count){
        Map<String ,Integer> map = new HashMap<>();
        map.put("count",Integer.valueOf(count));
        return sqlSession.selectList("selectAllRoll",map);
    }

}
