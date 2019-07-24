package com.hwhl.shengxian.entity;

import java.util.Date;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/7/17
 * @Version: 1.0
 */
public class Information {


    private Integer id;
    private String reason;
    private Integer customerId;
    private Integer sellerId;
    private String account;//发送的账号
    private Date createTime;//发送时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
