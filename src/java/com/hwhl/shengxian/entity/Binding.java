package com.hwhl.shengxian.entity;

import java.util.Date;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/25
 * @Version: 1.0
 */
public class Binding {

    private Integer id;
    private Integer sellerId;//关联商家表id
    private Integer customerId;//关联客户表id
    private Integer bingCategoryId;//关联绑定类别表id
    private Integer customerNumber;//客户编号
    private String customerName;//客户名称
    private String distribution;//最佳配送时间段
    private String illustrated1;//说明1
    private String illustrated2;//说明2
    private String remarks1;//备注1
    private String remarks2;//备注2
    private Date bindingTime;//绑定时间
    private Date updateTime;//更新时间
    private Integer isDelete;//0默认，1删除
    private Integer default1;//记录退出时上次登录的商家
    private Integer source;//来源，
    private Integer priceSchemeId;//



    //客户
    private Integer cid;
    private String CAccount;//客户账号
    private String password;
    private Integer power;

    //商家

    private Integer sellerNumber; //商家编号
    private String storeName; //商家名称
    private String name;  //区域
    private String SAccount; //商家账号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBingCategoryId() {
        return bingCategoryId;
    }

    public void setBingCategoryId(Integer bingCategoryId) {
        this.bingCategoryId = bingCategoryId;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getIllustrated1() {
        return illustrated1;
    }

    public void setIllustrated1(String illustrated1) {
        this.illustrated1 = illustrated1;
    }

    public String getIllustrated2() {
        return illustrated2;
    }

    public void setIllustrated2(String illustrated2) {
        this.illustrated2 = illustrated2;
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1;
    }

    public String getRemarks2() {
        return remarks2;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }

    public Date getBindingTime() {
        return bindingTime;
    }

    public void setBindingTime(Date bindingTime) {
        this.bindingTime = bindingTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getDefault1() {
        return default1;
    }

    public void setDefault1(Integer default1) {
        this.default1 = default1;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getPriceSchemeId() {
        return priceSchemeId;
    }

    public void setPriceSchemeId(Integer priceSchemeId) {
        this.priceSchemeId = priceSchemeId;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCAccount() {
        return CAccount;
    }

    public void setCAccount(String CAccount) {
        this.CAccount = CAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getSellerNumber() {
        return sellerNumber;
    }

    public void setSellerNumber(Integer sellerNumber) {
        this.sellerNumber = sellerNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSAccount() {
        return SAccount;
    }

    public void setSAccount(String SAccount) {
        this.SAccount = SAccount;
    }
}
