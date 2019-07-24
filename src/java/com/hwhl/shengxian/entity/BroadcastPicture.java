package com.hwhl.shengxian.entity;

/**
 * Description: 轮播图
 *
 * @Author: yang
 * @Date: 2018/6/27
 * @Version: 1.0
 */
public class BroadcastPicture {

    private Integer id;
    private String figure;//图片路径
    private String url;//跳转的URL
    private Integer sort;//排序
    private String distinguish;//区分

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDistinguish() {
        return distinguish;
    }

    public void setDistinguish(String distinguish) {
        this.distinguish = distinguish;
    }
}
