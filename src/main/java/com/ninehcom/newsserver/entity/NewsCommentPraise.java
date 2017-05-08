package com.ninehcom.newsserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/23.
 */
public class NewsCommentPraise implements Serializable{
    private Integer id;
    private Integer news_id;
    private Integer ref_id;
    private String user_id;
    private String ref_user_id;
    private Date create_at;

    public NewsCommentPraise() {
    }

    public NewsCommentPraise(Integer id,Integer news_id, Integer ref_id, String user_id, String ref_user_id, Date create_at) {
        this.id = id;
        this.news_id = news_id;
        this.ref_id = ref_id;
        this.user_id = user_id;
        this.ref_user_id = ref_user_id;
        this.create_at = create_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }

    public Integer getRef_id() {
        return ref_id;
    }

    public void setRef_id(Integer ref_id) {
        this.ref_id = ref_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRef_user_id() {
        return ref_user_id;
    }

    public void setRef_user_id(String ref_user_id) {
        this.ref_user_id = ref_user_id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

}
