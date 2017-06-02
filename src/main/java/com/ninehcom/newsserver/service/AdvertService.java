package com.ninehcom.newsserver.service;

import com.ninehcom.common.untils.Result;
import com.ninehcom.newsserver.entity.Advert;
import com.ninehcom.newsserver.mapper.AdvertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public Result selectAllAdvert() {
        ArrayList<Advert> advertList = (ArrayList)advertMapper.selectAllAdvert();
        return Result.Success(advertList);
    }

    public AdvertService(AdvertMapper  advertMapper){
        this.advertMapper = advertMapper;
    }

}
