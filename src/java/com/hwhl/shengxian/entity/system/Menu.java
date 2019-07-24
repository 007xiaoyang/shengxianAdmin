package com.hwhl.shengxian.entity.system;

import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/21
 * @Version: 1.0
 */
public class Menu {

    private Integer id;
    private String code;    //图标
    private String name;   //菜单名称
    private String link;    //路径，一级菜单没路径
    private Integer sort;   //顺序
    private Integer level; //记录归属哪个层级
    private Integer menuid; //层级，一级菜单为null，二级菜单对应一级菜单id
    private Integer isDele;

    private Integer lid;
    private List<Menus> listTwo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Integer getIsDele() {
        return isDele;
    }

    public void setIsDele(Integer isDele) {
        this.isDele = isDele;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public List<Menus> getListTwo() {
        return listTwo;
    }

    public void setListTwo(List<Menus> listTwo) {
        this.listTwo = listTwo;
    }
}