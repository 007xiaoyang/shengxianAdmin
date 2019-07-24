package com.hwhl.shengxian.entity.system;

import java.util.Date;

/**
 * Created by Administrator on 2018/4/12.
 */
public class AdminRole {
    private Integer id;
    private String rolename;
    private String role;
    private Date createtime;
    private Date updatetime;

    private String[] roles;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        if(createtime!=null) {
            this.createtime = new Date(createtime);
        }
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        if(updatetime!=null) {
            this.updatetime =new Date(updatetime);
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
