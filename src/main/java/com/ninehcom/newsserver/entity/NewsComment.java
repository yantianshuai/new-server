package com.ninehcom.newsserver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangbin on 2016/9/13.
 * 评论对象
 */
@Entity
public class NewsComment implements Serializable{
    @Id
    private Integer id;                 //评论id
    private Integer ref_id;             //引用id
    private Integer news_id;            //新闻id
    private String user_id;             //用户id
    private String ref_user_id;         //原评论用户的id引用
    private String context;             //评论的内容
    private Integer is_deleted;         //是否删除
    private Date create_at;             //创建时间
    private Date update_at;             //更新时间
    private Integer praises;            //点赞数量

    private Integer praise_id;          //点赞的id,如果此评论 与 当前用户有点赞的关系 则返回点赞的id

    private User author;                //当前评论者
//    private String user_nickname;       //当前评论的发出者昵称
//    private String user_iconurl;        //当前评论发出者的头像地址
//    private ArrayList<Tag> user_tags;   //当前评论发出者的标签集合
    private NewsComment ref_news_comment;  //上一级评论，如果评论的是评论，则把被评的评论对象存在此处返出去



    public NewsComment() {
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    //    public ArrayList<Tag> getUser_tags() {
//        return user_tags;
//    }
//
//    public void setUser_tags(ArrayList<Tag> user_tags) {
//        this.user_tags = user_tags;
//    }
//
//    public String getUser_iconurl() {
//        return user_iconurl;
//    }
//
//    public void setUser_iconurl(String user_iconurl) {
//        this.user_iconurl = user_iconurl;
//    }
//
//    public String getUser_nickname() {
//        return user_nickname;
//    }
//
//    public void setUser_nickname(String user_nickname) {
//        this.user_nickname = user_nickname;
//    }

    public NewsComment getRef_news_comment() {
        return ref_news_comment;
    }

    public void setRef_news_comment(NewsComment ref_news_comment) {
        this.ref_news_comment = ref_news_comment;
    }

    public Integer getPraise_id() {
        return praise_id;
    }

    public void setPraise_id(Integer praise_id) {
        this.praise_id = praise_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRef_id() {
        return ref_id;
    }

    public void setRef_id(Integer ref_id) {
        this.ref_id = ref_id;
    }

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public Integer getPraises() {
        return praises;
    }

    public void setPraises(Integer praises) {
        this.praises = praises;
    }
}
