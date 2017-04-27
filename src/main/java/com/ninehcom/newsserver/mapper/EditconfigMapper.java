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

    /**
     * 获取全部的配置项
     * @return
     */
    public List<Editconfig> selectAllEditconfig(){
        return sqlSession.selectList("selectAllEditconfig");
    }

    /**
     * 根据指定的KEY获取指定的配置项
     * @param key
     * @return
     */
    public Editconfig selectEditconfig(String key){
        Map<String,String> map = new HashMap<>();
            map.put("key",key);
        return sqlSession.selectOne("selectEditconfig",map);
    }

    /**
     * 获取客户端需要（ClientNeed=1）的配置项
     * @return
     */
    public List<Editconfig> selectClientEditconfig(){
        return sqlSession.selectList("selectClientEditconfig");
    }

}
