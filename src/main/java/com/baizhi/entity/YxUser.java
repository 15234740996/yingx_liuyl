package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

public class YxUser {
    @Excel(name="id")
    private String id;
    @Excel(name="昵称")
    private String nick_name;//昵称
    @Excel(name="手机号")
    private String phone;//手机号
    @Excel(name="头像",type = 2)
    private String pic_img;//头像
    @Excel(name="简介")
    private String brief;//简介
    @Excel(name="学分")
    private String score;//学分
    @Excel(name="创建时间")
    private Date create_date;//创建时间
    @Excel(name="状态")
    private String start;//状态

    public YxUser() {
        super();
    }

    @Override
    public String toString() {
        return "YxUser{" +
                "id='" + id + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", phone='" + phone + '\'' +
                ", pic_img='" + pic_img + '\'' +
                ", brief='" + brief + '\'' +
                ", score='" + score + '\'' +
                ", create_date=" + create_date +
                ", start='" + start + '\'' +
                '}';
    }

    public YxUser(String id, String nick_name, String phone, String pic_img, String brief, String score, Date create_date, String start) {
        this.id = id;
        this.nick_name = nick_name;
        this.phone = phone;
        this.pic_img = pic_img;
        this.brief = brief;
        this.score = score;
        this.create_date = create_date;
        this.start = start;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPic_img() {
        return pic_img;
    }

    public void setPic_img(String pic_img) {
        this.pic_img = pic_img;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
