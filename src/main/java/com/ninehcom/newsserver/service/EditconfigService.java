package com.ninehcom.newsserver.service;

import com.ninehcom.newsserver.entity.Editconfig;
import com.ninehcom.newsserver.enums.ConfigKeys;
import com.ninehcom.newsserver.mapper.EditconfigMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @PostConstruct
//    public void init() {
//
//        editconfigMapper.createUserInfoTable();
//        editconfigMapper.createEditConfigTable();
//        editconfigMapper.createActionTable();
//        editconfigMapper.createUserStatisticsTable();
//        editconfigMapper.createUserActionTable();
//        editconfigMapper.createUserScoreTable();
//        editconfigMapper.createVersionTable();
//
//        List<Editconfig> configs = new ArrayList<>();
//
//        configs.add(new Editconfig(1, "TeamId", "1", "俱乐部Id"));
//        configs.add(new Editconfig(2, "UserUrl", "https://test-account.9h-sports.com", "用户中心服务URL标识（测试：test 上线：api）"));
//        configs.add(new Editconfig(3, "AppID", "3", "用户中心的应用ID"));
//        configs.add(new Editconfig(4, "SmsUrl", "http://sms.9hgame.com/JHSMSService.asmx", "短信中心"));
//        configs.add(new Editconfig(5, "LoginTimeout", "-1", "超时时间(-1为永不超时，单位分钟)"));
//        configs.add(new Editconfig(6, "MaxScore", "15", "每日最大积分"));
//        configs.add(new Editconfig(7, "MaxExperience", "15", "每日最大经验"));
//        configs.add(new Editconfig(8, "AndroidLoadURL", "安卓安装地址", "安卓最新版本下载地址"));
//        configs.add(new Editconfig(9, "IOSLoadURL", "苹果安装地址", "苹果最新版本下载地址"));
//        configs.add(new Editconfig(10, "SensitiveWordURL", "http://123.59.84.71:8083/check", "政治敏感词服务地址"));
//        configs.add(new Editconfig(11, "NickNameMaxChangeTime", "1", "昵称最大修改次数(-1为不限制)"));
//
//        for (int i = 0; i < configs.size(); i++) {
//            initConfig(configs.get(i));
//        }
//
////        this.configs = editconfigMapper.selectAllEditconfig();
////        mapping = new HashMap<>();
////        for (Editconfig config : this.configs) {
////            mapping.put(config.getKey(), config.getValue());
////        }
//        try {
//            editconfigMapper.initAction(1, "签到", "用户签到", 1, 1);
//            editconfigMapper.initAction(2, "回复", "回复帖子", 1, 1);
//            editconfigMapper.initAction(3, "其他用户回复", "其他用户回复", 1, 1);
//            editconfigMapper.initAction(4, "发帖", "发帖", 3, 3);
//            editconfigMapper.initAction(5, "分享", "分享", 2, 2);
//            editconfigMapper.initAction(6, "点赞", "点赞", 1, 1);
//            editconfigMapper.initAction(7, "购买商品", "购买商品", 0, 0);
//            editconfigMapper.initAction(8, "竞猜", "竞猜", 0, 0);
//            editconfigMapper.initAction(9, "补签", "用户补签", 1, 1);
//        } catch (Exception ex) {
//
//        }
//        try {
//            editconfigMapper.initVersion(1, 1, "Android", "0.0.0.0");
//            editconfigMapper.initVersion(2, 2, "IOS", "0.0.0.0");
//        } catch (Exception ex) {
//
//        }
//    }

    public String getConfigValue(String key) {
        Editconfig config = editconfigMapper.selectEditconfig(key);
        if (config != null) {
            return config.getValue();
        } else {
            return null;
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
