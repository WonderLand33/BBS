package com.honey.entity.extend;

import java.util.Date;

/**
 * @description 回复
 * @author: chenPeng
 * @date: 2015/9/10 11:21
 * Copyright © 2015/9/10 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public class ReplyVo {

    private Integer id;
    private String msg;
    private Integer up;
    private String ua;
    private Date createTime;
    private Integer memberId;
    private String nikename;
    private String headImg;
    private String articleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getHeadImg() {
        return headImg==null?"http://ww2.sinaimg.cn/large/a15b4afegw1ewa1x4ja0wj201s01s3y9.jpg":headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
