package com.ninehcom.newsserver.entity;

import java.io.Serializable;

/**
 * Created by zhangbin on 2016/9/22.
 * 添加新闻评论时的请求body体
 */

public class RequestAddNewsComment implements Serializable {
    private String user_id;
    private String context;
    private Integer ref_id;
    private String ref_user_id;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getRef_id() {
        return ref_id;
    }

    public void setRef_id(Integer ref_id) {
        this.ref_id = ref_id;
    }

    public String getRef_user_id() {
        return ref_user_id;
    }

    public void setRef_user_id(String ref_user_id) {
        this.ref_user_id = ref_user_id;
    }
}
