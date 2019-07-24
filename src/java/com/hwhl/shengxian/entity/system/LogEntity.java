package com.hwhl.shengxian.entity.system;

/**
 * Description: 日志实体类对象
 *
 * @Author: yang
 * @Date: 2018/7/27
 * @Version: 1.0
 */
public class LogEntity {


    private String userid;//登录的账号
    private String module;//执行的模块
    private String method;//执行的方法
    private String rsponse_data;//响应时间
    //private String IP;//IP地址
    private String data;//执行时间
    private String commite;//执行描述

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRsponse_data() {
        return rsponse_data;
    }

    public void setRsponse_data(String rsponse_data) {
        this.rsponse_data = rsponse_data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCommite() {
        return commite;
    }

    public void setCommite(String commite) {
        this.commite = commite;
    }
}
