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

//    private Map<String, String> mapping = null;
    @Autowired
    private EditconfigMapper editconfigMapper;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private void initConfig(Editconfig config) {
        try {
            editconfigMapper.initEditConfig(config);
        } catch (Exception ex) {

        }
    }

    public Result getConfigValue(String key) {
        Editconfig config = editconfigMapper.selectEditconfig(key);
        if (config != null) {
            return Result.Success(config.getValue());
        } else {
            return Result.Success();
        }
    }

    private String getValue(String key) {
        Editconfig config = editconfigMapper.selectEditconfig(key);
        if (config != null) {
            return config.getValue();
        } else {
            return null;
        }
    }

    public Result getValues(){
        ArrayList<Editconfig> configs = (ArrayList<Editconfig>) editconfigMapper.selectAllEditconfig();
        if (configs != null) {
            return Result.Success(configs);
        } else {
            return null;
        }
    }

    public String getValue(ConfigKeys configKeys) {
        return getValue(configKeys.toString());
    }

    public Date getDate(ConfigKeys configkeys) {
        try {
            return format.parse(getValue(configkeys));
        } catch (ParseException ex) {
            return null;
        }
    }

    public Integer getInt(ConfigKeys configkeys) {
        return Integer.parseInt(getValue(configkeys));
    }

    public Double getDouble(ConfigKeys configkeys) {
        return Double.parseDouble(getValue(configkeys));
    }

    public Float getFloat(ConfigKeys configkeys) {
        return Float.parseFloat(getValue(configkeys));
    }

    public Long getLong(ConfigKeys configkeys) {
        return Long.parseLong(getValue(configkeys));
    }

    public JSONObject getJson(ConfigKeys configkeys) {
        try {
            return new JSONObject(getValue(configkeys));
        } catch (JSONException ex) {
            return null;
        }
    }

    public JSONArray getJsonArray(ConfigKeys configkeys) {
        try {
            return new JSONArray(getValue(configkeys));
        } catch (JSONException ex) {
            return null;
        }
    }
}
