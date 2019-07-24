package com.hwhl.shengxian.entity;

import com.hwhl.shengxian.util.Page;

import java.util.Date;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/27
 * @Version: 1.0
 */
public class Notice {


    private Integer id;
    private String noticeContent; //内容
    private Integer distinguish;//区分
    private Date releaseTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Integer getDistinguish() {
        return distinguish;
    }

    public void setDistinguish(Integer distinguish) {
        this.distinguish = distinguish;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }
}
