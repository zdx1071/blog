package com.gordon.blog.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by gordon.zhang on 2018/2/8.
 */
@Entity
public class Blog implements Serializable{

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String summary;

    private String content;

    private Integer readSize = 0;

    private Integer commentSize = 0;

    private Integer voteSize = 0;

    //一个博客对应一个分类
    @OneToOne
    private Catalog catalog;

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        catalog = catalog;
    }

    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }

    public Integer getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(Integer voteSize) {
        this.voteSize = voteSize;
    }



    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    @org.hibernate.annotations.CreationTimestamp    //由数据库自动创建时间
    private Timestamp publishTime;

    protected Blog() {
    }

    public Blog(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }
}
