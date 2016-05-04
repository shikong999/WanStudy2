package com.university.wanstudy.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 课程Model
 * Created by Wucheng on 2016/4/20.
 */
@Table(name = "major")
public class MajorModel {


    /**
     * id : 23
     * createdAt : 2014-11-20T13:20:36.000Z
     * updatedAt : 2016-03-21T11:30:00.000Z
     * name : 日语五十音图语音入门
     * majorId : 10
     * structure : linear
     * logo : 0960d34f-0b64-43d5-9a58-efdc40f6a31e..jpg
     * banner : 42aff902-0c96-4347-b16b-b78217e7e5b5..jpg
     * code : YY
     * attachment :
     * description : 日本语的五十音图及一些日语的发音知识，配合生动有趣的动漫辅助发音。
     * shortDescription : 16讲轻松掌握日语50音图，日语进阶之路为您打开
     * teacherAvatar : 4937e404-bfc7-49eb-9b64-c9657cac8830..jpg
     * teacherName : 刘苏曼
     * numOfClasses : 16
     * file : http://pan.baidu.com/s/1hqEnh0w
     * teacherDescription : 刘苏曼，北京大学日语博士在读，著有《简单日语极速入门》。曾获第16届中日友好之声日语演讲比赛低年级组第1名，曾赴日本京都大学交换留学。多场口译、笔译、主持经验，曾负责东北亚学生论坛即席翻译。
     * hide : 0
     * timePerPart : 15
     * numDemo : null
     * qqGroup : 【万门大学日语交流平台】想要进群与老师、同学更多互动，请先加万门官方微信号“小万君：wanmen007”为好友，备注“我要进日语群”。
     * courseVideoLink :
     * order : 1
     * logoUrl : http://img.wanmen.org/course/logo/0960d34f-0b64-43d5-9a58-efdc40f6a31e..jpg
     * teacherAvatarUrl : http://img.wanmen.org/course/teacher_avatar/4937e404-bfc7-49eb-9b64-c9657cac8830..jpg
     * smallImgUrl : http://img.wanmen.org/course/small_img/4e084cf2-1495-40d0-9f5f-729d0ae18278..jpg
     * bannerUrl : null
     * bannerTvUrl : null
     * bigImgUrl : http://img.wanmen.org/images/781018a5-a643-42f2-add2-94502225830b.jpeg
     * smallImgMobileUrl : http://img.wanmen.org/images/655d1c64-1fff-44fa-b394-94feedade50c.jpeg
     * smallImgTvUrl : null
     * bigImgTvUrl : null
     * teacherAvatarTvUrl : null
     * logoTvUrl : null
     * price : null
     * promotion : null
     * homeSmallImgUrl : http://img.wanmen.org/images/cdbad2d7-58d1-46f8-ba9d-ea52145b69a0.jpeg
     * major_id : 10
     * daysLeft : 365
     * isPaid : true
     */
    @Column(name = "id",isId = true,autoGen = false)
    private int id;
    private String createdAt;
    private String updatedAt;
    @Column(name = "name")
    private String name;
    private int majorId;
    private String structure;
    private String logo;
    private String banner;
    private String code;
    private String attachment;
    private String description;
    @Column(name = "shortDescription")
    private String shortDescription;
    private String teacherAvatar;
    @Column(name = "teacherName")
    private String teacherName;
    @Column(name = "numOfClasses")
    private int numOfClasses;
    private String file;
    private String teacherDescription;
    private int hide;
    @Column(name = "timePerPart")
    private int timePerPart;
    private Object numDemo;
    private String qqGroup;
    private String courseVideoLink;
    private int order;
    @Column(name = "logoUrl")
    private String logoUrl;
    private String teacherAvatarUrl;
    private String smallImgUrl;
    private Object bannerUrl;
    private Object bannerTvUrl;
    private String bigImgUrl;
    private String smallImgMobileUrl;
    private Object smallImgTvUrl;
    private Object bigImgTvUrl;
    private Object teacherAvatarTvUrl;
    private Object logoTvUrl;
    private Object price;
    private Object promotion;
    private String homeSmallImgUrl;
    @Column(name = "major_id")
    private int major_id;
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

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getTeacherAvatar() {
        return teacherAvatar;
    }

    public void setTeacherAvatar(String teacherAvatar) {
        this.teacherAvatar = teacherAvatar;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getNumOfClasses() {
        return numOfClasses;
    }

    public void setNumOfClasses(int numOfClasses) {
        this.numOfClasses = numOfClasses;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTeacherDescription() {
        return teacherDescription;
    }

    public void setTeacherDescription(String teacherDescription) {
        this.teacherDescription = teacherDescription;
    }

    public int getHide() {
        return hide;
    }

    public void setHide(int hide) {
        this.hide = hide;
    }

    public int getTimePerPart() {
        return timePerPart;
    }

    public void setTimePerPart(int timePerPart) {
        this.timePerPart = timePerPart;
    }

    public Object getNumDemo() {
        return numDemo;
    }

    public void setNumDemo(Object numDemo) {
        this.numDemo = numDemo;
    }

    public String getQqGroup() {
        return qqGroup;
    }

    public void setQqGroup(String qqGroup) {
        this.qqGroup = qqGroup;
    }

    public String getCourseVideoLink() {
        return courseVideoLink;
    }

    public void setCourseVideoLink(String courseVideoLink) {
        this.courseVideoLink = courseVideoLink;
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

    public String getTeacherAvatarUrl() {
        return teacherAvatarUrl;
    }

    public void setTeacherAvatarUrl(String teacherAvatarUrl) {
        this.teacherAvatarUrl = teacherAvatarUrl;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }

    public Object getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(Object bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Object getBannerTvUrl() {
        return bannerTvUrl;
    }

    public void setBannerTvUrl(Object bannerTvUrl) {
        this.bannerTvUrl = bannerTvUrl;
    }

    public String getBigImgUrl() {
        return bigImgUrl;
    }

    public void setBigImgUrl(String bigImgUrl) {
        this.bigImgUrl = bigImgUrl;
    }

    public String getSmallImgMobileUrl() {
        return smallImgMobileUrl;
    }

    public void setSmallImgMobileUrl(String smallImgMobileUrl) {
        this.smallImgMobileUrl = smallImgMobileUrl;
    }

    public Object getSmallImgTvUrl() {
        return smallImgTvUrl;
    }

    public void setSmallImgTvUrl(Object smallImgTvUrl) {
        this.smallImgTvUrl = smallImgTvUrl;
    }

    public Object getBigImgTvUrl() {
        return bigImgTvUrl;
    }

    public void setBigImgTvUrl(Object bigImgTvUrl) {
        this.bigImgTvUrl = bigImgTvUrl;
    }

    public Object getTeacherAvatarTvUrl() {
        return teacherAvatarTvUrl;
    }

    public void setTeacherAvatarTvUrl(Object teacherAvatarTvUrl) {
        this.teacherAvatarTvUrl = teacherAvatarTvUrl;
    }

    public Object getLogoTvUrl() {
        return logoTvUrl;
    }

    public void setLogoTvUrl(Object logoTvUrl) {
        this.logoTvUrl = logoTvUrl;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getPromotion() {
        return promotion;
    }

    public void setPromotion(Object promotion) {
        this.promotion = promotion;
    }

    public String getHomeSmallImgUrl() {
        return homeSmallImgUrl;
    }

    public void setHomeSmallImgUrl(String homeSmallImgUrl) {
        this.homeSmallImgUrl = homeSmallImgUrl;
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
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
