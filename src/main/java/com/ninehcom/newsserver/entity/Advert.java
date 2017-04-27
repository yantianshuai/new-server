package com.ninehcom.newsserver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Advert实体类，将同时用于Mybatis和JPA使用
 * @author shenjizhe
 * @version 1.0.0
 * 
    Id            |     int(11)       |  唯一标识
    Logo          |     varchar(128)  |  广告图
    Title         |     varchar(32)   |  标题
    Links         |     varchar(128)  |  连接
    Sort          |     int(11)       |  排序值
    PublishTime    |     Date          |  发布时间
 */
@Entity
public class Advert {

    @Id
    private int Id;
    public  int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    private String Logo;
    public  String getLogo() {
        return Logo;
    }
    public void setLogo(String Logo) {
        this.Logo = Logo;
    }

    private String Title;
    public  String getTitle() {
        return Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }

    private String Links;
    public  String getLinks() {
        return Links;
    }
    public void setLinks(String Links) {
        this.Links = Links;
    }

    private int Sort;
    public  int getSort() {
        return Sort;
    }
    public void setSort(int Sort) {
        this.Sort = Sort;
    }
    
    private Date PublishTime;
    public  Date getPublishTime() {
        return PublishTime;
    }
    public void setPublishTime(Date PublishTime) {
        this.PublishTime = PublishTime;
    }
}
