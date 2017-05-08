package com.ninehcom.newsserver.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/23.
 */
public class RequestNewsCommentPraiseBody implements Serializable{
    private String user_id;
//    private Integer praise_id;
//
//    public Integer getPraise_id() {
//        return praise_id;
//    }

//    public void setPraise_id(Integer praise_id) {
//        this.praise_id = praise_id;
//    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
