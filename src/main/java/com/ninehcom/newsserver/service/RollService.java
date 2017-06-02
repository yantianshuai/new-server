package com.ninehcom.newsserver.service;

import com.ninehcom.common.untils.Result;
import com.ninehcom.newsserver.entity.Roll;
import com.ninehcom.newsserver.mapper.RollMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Roll的Service
 *
 * @author shenjizhe
 * @version 1.0.0
 */
@Service
public class RollService {

    @Autowired
    private RollMapper rollMapper;

    public Result selectAllRoll(int count) {
        ArrayList<Roll> rollList = (ArrayList)rollMapper.selectAllRoll(count);
        return Result.Success(rollList);
    }

}
