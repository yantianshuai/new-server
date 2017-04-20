package com.ninehcom.newsserver.mapper;


import com.ninehcom.newsserver.entity.Editconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class EditconfigMapper extends baseMapper{

    public List<Editconfig> selectAllEditconfig(){
        return sqlSession.selectList("selectAllEditconfig");
    }
}
