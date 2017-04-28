package com.ninehcom.newsserver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
    名称:Roll实体类
    描述:滚动
 * @author shenjizhe
 * @version 1.0.0

### 滚动 ###
<A NAME="Roll">Roll</A>

名称|类型|描述
-|-|-
Id                  |Integer   |逻辑主键
Logo                |String    |主图
Title               |String    |标题
Abstracts           |String    |摘要
Links               |String    |链接
Sort                |Integer   |顺序
StateId             |Integer   |状态。1、上线；2、下线
PublishTime         |Date      |发布时间
 */
@Entity
public class Roll{

    @Id
    private Integer Id;
    public  Integer getId() {
        return Id;
    }
    public void setId(Integer Id) {
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

    private String Abstracts;
    public  String getAbstracts() {
        return Abstracts;
    }
    public void setAbstracts(String Abstracts) {
        this.Abstracts = Abstracts;
    }

    private String Links;
    public  String getLinks() {
        return Links;
    }
    public void setLinks(String Links) {
        this.Links = Links;
    }

    private Integer Sort;
    public  Integer getSort() {
        return Sort;
    }
    public void setSort(Integer Sort) {
        this.Sort = Sort;
    }

    private Integer StateId;
    public  Integer getStateId() {
        return StateId;
    }
    public void setStateId(Integer StateId) {
        this.StateId = StateId;
    }

    private Date PublishTime;
    public  Date getPublishTime() {
        return PublishTime;
    }
    public void setPublishTime(Date PublishTime) {
        this.PublishTime = PublishTime;
    }

}