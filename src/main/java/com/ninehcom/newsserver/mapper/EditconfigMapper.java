package com.ninehcom.newsserver.mapper;

import com.ninehcom.newsserver.entity.Editconfig;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/21.
 */
@Repository
public class EditConfigMapper extends BaseMapper {

    public List<Editconfig> selectAllEditconfig(){
        return sqlSession.selectList("selectAllEditconfig");
    }

    public int initEditConfig(Editconfig config){
        Map<String,String> map = new HashMap<>();
        map.put("Id",String.valueOf(config.getId()));
        map.put("Key",config.getKey());
        map.put("Value",config.getValue());
        map.put("Remark",config.getRemark());
        return sqlSession.insert("initEditConfig",map);
    }

    public Editconfig selectEditconfig(String key){
        Map<String,String> map = new HashMap<>();
        map.put("Key",key);
        return sqlSession.selectOne("selectEditconfig",map);
    }

}
