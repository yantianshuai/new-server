package com.ninehcom.newsserver.entity;

import java.io.Serializable;

/**
 * Created by zhangbin on 2017/3/7.
 * 用于组织新闻内容
 */
public class NewsContents implements Serializable{

    private String type;

    private String value;

    public NewsContents() {
    }

    public NewsContents(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
