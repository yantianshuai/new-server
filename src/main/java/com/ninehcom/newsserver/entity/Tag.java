package com.ninehcom.newsserver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/18.
 */
@Entity
public class Tag implements Serializable {

    @Id
    private Integer id;
    public  Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    private String key;
    public  String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    private String title;
    public  String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    private String iconUrl;
    public  String getIconUrl() {
        return iconUrl;
    }
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

}