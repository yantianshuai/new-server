package com.ninehcom.newsserver.mapper;

import com.ninehcom.newsserver.entity.Editconfig;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Editconfig的Mapper，用于Mybatis
 *
 * @author shenjizhe
 * @version 1.0.0
 */
@Repository
public class EditconfigMapper extends BaseMapper{

    public List<Editconfig> selectAllEditconfig(){
        return sqlSession.selectList("selectAllEditconfig");
    }

    public Editconfig selectEditconfig(String key){
        Map<String,String> map = new HashMap<>();
            map.put("key",key);
        return sqlSession.selectOne("selectEditconfig",map);
    }

    public int initEditConfig(Editconfig config){
        Map<String,String> map = new HashMap<>();
            map.put("Id",String.valueOf(config.getId()));
            map.put("Key",config.getKey());
            map.put("Value",config.getValue());
            map.put("Remark",config.getRemark());
        return sqlSession.insert("initEditConfig",map);
    }

}
