package com.ninehcom.newsserver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * News实体类，将同时用于Mybatis和JPA使用
 *
 * @author shenjizhe
 * @version 1.0.0 Id	唯一标识 NewsTypeId	类型ID (1	公告 2 球队 3 比赛 4 视频) NewsTypeName
 * 类型名称 Logo	图片 Title	标题 Abstracts	摘要 Contents	内容 Links	超级链接 ReadTimes	读取的次数
 * SourceId	来源ID SourceName	来源名称 CreateTime	创建时间 UpdateTime	修改时间 PublishTime 发布时间
 */
@Entity
public class News implements Serializable {

    @Id
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    private int NewsTypeId;

    public int getNewsTypeId() {
        return NewsTypeId;
    }

    public void setNewsTypeId(int NewsTypeId) {
        this.NewsTypeId = NewsTypeId;
    }

    private String NewsTypeName;

    public String getNewsTypeName() {
        return NewsTypeName;
    }

    public void setNewsTypeName(String NewsTypeName) {
        this.NewsTypeName = NewsTypeName;
    }

    private int ModeTypeId;

    public int getModeTypeId() {
        return ModeTypeId;
    }

    public void setModeTypeId(int ModeTypeId) {
        this.ModeTypeId = ModeTypeId;
    }

    private String Logo;

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String Logo) {
        this.Logo = Logo;
    }

    private String Title;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    private String Abstracts;

    public String getAbstracts() {
        return Abstracts;
    }

    public void setAbstracts(String Abstracts) {
        this.Abstracts = Abstracts;
    }

    private String Contents;

    public String getContents() {
        return Contents;
    }

    public void setContents(String Contents) {
        this.Contents = Contents;
    }

    private ArrayList<NewsContents> sections;

    public ArrayList<NewsContents> getSections() {
        return sections;
    }

    public void setSections(ArrayList<NewsContents> sections) {
        this.sections = sections;
    }

    private String Links;

    public String getLinks() {
        return Links;
    }

    public void setLinks(String Links) {
        this.Links = Links;
    }

    private int ReadTimes;

    public int getReadTimes() {
        return ReadTimes;
    }

    public void setReadTimes(int ReadTimes) {
        this.ReadTimes = ReadTimes;
    }

    private int SourceId;

    public int getSourceId() {
        return SourceId;
    }

    public void setSourceId(int SourceId) {
        this.SourceId = SourceId;
    }

    private String SourceName;

    public String getSourceName() {
        return SourceName;
    }

    public void setSourceName(String SourceName) {
        this.SourceName = SourceName;
    }

    private Date CreateTime;

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    private Date PublishTime;

    public Date getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(Date PublishTime) {
        this.PublishTime = PublishTime;
    }

    private Date UpdateTime;

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    private Integer CommentCount;       //新闻的评论个数

    public Integer getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(Integer commentCount) {
        CommentCount = commentCount;
    }

    private Integer CanComment;

    public Integer getCanComment() {
        return CanComment;
    }

    public void setCanComment(Integer canComment) {
        CanComment = canComment;
    }
}
