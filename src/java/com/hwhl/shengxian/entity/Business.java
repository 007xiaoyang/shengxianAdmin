package com.hwhl.shengxian.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Description: 商家
 *
 * @Author: yang
 * @Date: 2018/6/14
 * @Version: 1.0
 */
public class Business {

    private Integer id;
    private Integer if_adopt;
    private Integer category_id; //关联商家类别表ID
    private String number; //编号
    private String phone; //手机号
    private String password;
    private String name; //名称
    private String store_name; //商店名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date duration; //使用期限
    private String TDCode; //店铺二维码路径
    private String token;
    private double starting_price;// 起送价
    private String confine;// 配送范围
    private String business_hours; //营业时间
    private String notice; //公告
    private Integer power; //登录权限，0默认一人登录，1多人登录
    private String invitation; //邀请人号码
    private String illustrated; //说明
    private String remarks; //备注
    private String device_id; //设备id
    private Date create_time; //创建时间
    private Date update_time; //
    private int is_disable; //0默认，1禁用
    private int is_del; //0默认,1删除

    private String surplus;

    private Integer days;//记录剩余天数


    private int open; //1已购买使用权
    private int status ; //1退出APP免登陆

    public Business() {
    }

    public Business(String number, String phone, String password,String token , String name, String store_name, String illustrated, String remarks, Date create_time) {
        this.number = number;
        this.phone = phone;
        this.password = password;
        this.token = token;
        this.name = name;
        this.store_name = store_name;
        this.illustrated = illustrated;
        this.remarks = remarks;
        this.create_time = create_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIf_adopt() {
        return if_adopt;
    }

    public void setIf_adopt(Integer if_adopt) {
        this.if_adopt = if_adopt;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getTDCode() {
        return TDCode;
    }

    public void setTDCode(String TDCode) {
        this.TDCode = TDCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getStarting_price() {
        return starting_price;
    }

    public void setStarting_price(double starting_price) {
        this.starting_price = starting_price;
    }

    public String getConfine() {
        return confine;
    }

    public void setConfine(String confine) {
        this.confine = confine;
    }

    public String getBusiness_hours() {
        return business_hours;
    }

    public void setBusiness_hours(String business_hours) {
        this.business_hours = business_hours;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getInvitation() {
        return invitation;
    }

    public void setInvitation(String invitation) {
        this.invitation = invitation;
    }

    public String getIllustrated() {
        return illustrated;
    }

    public void setIllustrated(String illustrated) {
        this.illustrated = illustrated;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getIs_disable() {
        return is_disable;
    }

    public void setIs_disable(int is_disable) {
        this.is_disable = is_disable;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }

    public void setIs_disable(Integer is_disable) {
        this.is_disable = is_disable;
    }

    public Integer getIs_del() {
        return is_del;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }

    public String getSurplus() {
        return surplus;
    }

    public void setSurplus(String surplus) {
        this.surplus = surplus;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
