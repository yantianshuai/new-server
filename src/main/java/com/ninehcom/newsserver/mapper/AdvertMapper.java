package com.ninehcom.newsserver.mapper;

import com.ninehcom.newsserver.entity.Advert;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Advert广告的Mapper，用于Mybatis
 * @author shenjizhe
 * @version 1.0.0
 */
@Repository
public class AdvertMapper extends BaseMapper {
    /**
     * 查询所有状态为“上线”的广告
     * @return
     */
    public List<Advert> selectAllAdvert(){
        return sqlSession.selectList("selectAllAdvert");
    }

}
