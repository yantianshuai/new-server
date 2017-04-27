package com.ninehcom.newsserver.service;

import com.ninehcom.newsserver.entity.Advert;
import com.ninehcom.newsserver.mapper.AdvertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Advert的Service
 * @author shenjizhe
 * @version 1.0.0
 */
@Service
public class AdvertService {

    @Autowired
    private  AdvertMapper  advertMapper;
    public List<Advert> selectAllAdvert() {
        List<Advert> advertList = advertMapper.selectAllAdvert();
        return advertList;
    }
}
