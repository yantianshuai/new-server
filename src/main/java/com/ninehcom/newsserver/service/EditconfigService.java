package com.ninehcom.newsserver.service;

import com.ninehcom.common.untils.Result;
import com.ninehcom.newsserver.entity.Editconfig;
import com.ninehcom.common.enums.ConfigKeys;
import com.ninehcom.newsserver.mapper.EditconfigMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.ReaderContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Editconfig的Service
 *
 * @author shenjizhe
 * @version 1.0.0
 */
@Service
public class EditconfigService {

    @Autowired
    private EditconfigMapper editconfigMapper;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static int ClubID;

    @PostConstruct
    public void init(){
        ClubID = Integer.parseInt(getValueById(ConfigKeys.TeamId));
    }

    /**
     * 通过key获取指定的配置文件
     * @param key
     * @return
     */
    public Result getValueById(String key) {
        Editconfig config = editconfigMapper.selectEditconfig(key);
        if (config != null) {
            return Result.Success(config.getValue());
        } else {
            return Result.Success();
        }
    }
    public String getValueById(ConfigKeys configKeys) {
        return getValueById(configKeys.toString()).getTag().toString();
    }

    /**
     * 获取全部的配置文件，包括客户端不可见的配置项
     * @return
     */
    public Result selectAllEditConfig(){
        ArrayList<Editconfig> configs = (ArrayList<Editconfig>) editconfigMapper.selectAllEditconfig();
        if (configs != null) {
            return Result.Success(configs);
        } else {
            return null;
        }
    }

    /**
     * 获取只有客户端可见的配置项
     * @return
     */
    public Result selectClientEditconfig() {
        ArrayList<Editconfig> editconfigs = (ArrayList<Editconfig>) editconfigMapper.selectClientEditconfig();
        return Result.Success(editconfigs);
    }



}
