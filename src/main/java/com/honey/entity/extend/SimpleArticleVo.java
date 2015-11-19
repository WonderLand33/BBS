package com.honey.entity.extend;

import java.util.Date;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/31 16:04
 * Copyright © 2015/8/31 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public class SimpleArticleVo {

    private Integer id; //标题ID
    /*private Integer memberId;//用户ID*/
    private String title;//标题
    private Date createTime;//创建时间
    private String sname;//分类短字
    private Integer up;//"喜欢" 数量
    private String headImg;//楼主头像
    private String nikename;//楼主名字
    private Integer replys;//回复 数量


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public String getHeadImg() {
        return headImg==null?"http://ww2.sinaimg.cn/large/a15b4afegw1ewa1x4ja0wj201s01s3y9.jpg":headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public Integer getReplys() {
        return replys;
    }

    public void setReplys(Integer replys) {
        this.replys = replys;
    }
}
