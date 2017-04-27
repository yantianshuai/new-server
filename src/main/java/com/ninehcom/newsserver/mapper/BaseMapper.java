package com.ninehcom.newsserver.mapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Administrator on 2017/4/27.
 */
public class BaseMapper {

    @Autowired
    @Qualifier("sqlSessionTemplate")
    protected SqlSession sqlSession;

}
