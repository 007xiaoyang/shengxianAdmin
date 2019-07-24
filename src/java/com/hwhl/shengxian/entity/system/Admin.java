package com.hwhl.shengxian.entity.system;

import java.util.Date;

/**
 * Created by Administrator on 2018/4/11.
 */
public class Admin {
    private Integer id;
    private String account;
    private String username;
    private String rolename;
    private Date logintime;
    private Integer jur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Long logintime) {
        if(logintime!=null) {
            this.logintime =new Date(logintime);
        }
    }

    public Integer getJur() {
        return jur;
    }

    public void setJur(Integer jur) {
        this.jur = jur;
    }
}
