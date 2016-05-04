package com.university.wanstudy.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 专业Model
 * Created by Wucheng on 2016/4/20.
 */
@Table(name = "genre")
public class GenreModel {

    /**
     * id : 17
     * createdAt : 2014-12-19T08:03:34.000Z
     * updatedAt : 2016-04-06T10:19:02.000Z
     * name : 高中数学
     * description : 【网红学霸的一曲魔性数学课】：主讲老师刘畅，学生心中的数学学霸男神，课程从基础概念入手，难度梯次上升，基础概念讲解配套习题讲解，视频适合于高一在读学生弥补必修一的漏洞；适用于国外的初高中学生，迅速掌握函数相关知识，高三的学生也可以进行复习，准确把握高考命题脉络。
     * genreId : 2
     * logo : d0fdc868-0732-4df9-935b-fd43799bc206..png
     * code : GM
     * order : 1
     * logoUrl : http://img.wanmen.org/major/logo/d0fdc868-0732-4df9-935b-fd43799bc206..png
     * price : 299
     * promotion : 主讲老师刘畅，学生心中的数学学霸男神，课程从基础概念入手，难度梯次上升，基础概念讲解配套习题讲解，视频适合于高一在读学生弥补必修一的漏洞；适用于国外的初高中学生，迅速掌握函数相关知识，高三的学生也可以进行复习，准确把握高考命题脉络。
     * genre_id : 2
     * daysLeft : -1
     * isPaid : false
     */
    @Column(name = "id",isId = true,autoGen = false)
    private int id;
    private String createdAt;
    private String updatedAt;
    @Column(name = "name")
    private String name;
    private String description;
    private int genreId;
    private String logo;
    private String code;
    private int order;
    @Column(name = "logoUrl")
    private String logoUrl;
    private int price;
    private String promotion;
    @Column(name = "genre_id")
    private int genre_id;
    private int daysLeft;
    private boolean isPaid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
}
