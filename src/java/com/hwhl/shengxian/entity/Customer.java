package com.hwhl.shengxian.entity;

import java.util.Date;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/25
 * @Version: 1.0
 */
public class Customer {

    private Integer id;
    private String phone;
    private String password;
    private Date cate_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCate_time() {
        return cate_time;
    }

    public void setCate_time(Date cate_time) {
        this.cate_time = cate_time;
    }
}
