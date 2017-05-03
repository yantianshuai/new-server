package com.ninehcom.newsserver.entity;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/9/22.
 */

public class User implements Serializable {


    // id        自增长(长整,以下所有id字段皆为该规则)
    private String userId;


    private String nickName;

    // avatar    头像图片路径
    private String avatar;

    // true_name 真实姓名
    private String trueName;

    // sex       性别 1:男 2:女 3:保密
    private String sex;


    // birth     出生年月日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;



    private  Date createdAt;

    private Date updatedAt;

    private Integer level;

    private String lvName;

    private Integer attentionCount;

    private Integer fansCount;

    private Integer topicCount;

    private List<Tag> tagList;

    public User() {
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }


    public String getLvName() {
        return lvName;
    }

    public void setLvName(String lvName) {
        this.lvName = lvName;
    }

    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    /**
     * 将json格式的字符串解析成Map对象 <li>
     * json格式：{"name":"admin","retries":"3fff","testname"
     * :"ddd","testretries":"fffffffff"}
     */
    private static HashMap<String, Object> toHashMap(JSONObject jsonObject) throws JSONException {
        HashMap<String, Object> data = new HashMap<String, Object>();
        // 将json字符串转换成jsonObject

        Iterator it = jsonObject.keys();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext())
        {
            String key = String.valueOf(it.next());
            Object value =  jsonObject.get(key);
            data.put(key, value);
        }
        return data;
    }
}
