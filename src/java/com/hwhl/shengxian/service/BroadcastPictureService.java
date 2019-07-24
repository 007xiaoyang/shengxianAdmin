package com.hwhl.shengxian.service;

import com.hwhl.shengxian.entity.BroadcastPicture;
import com.hwhl.shengxian.util.Page;

import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/27
 * @Version: 1.0
 */
public interface BroadcastPictureService {


    /**
     * 添加轮播图
     * @param broadcastPicture
     * @return
     */
    Integer addBroadcastPicture(BroadcastPicture broadcastPicture);


    /**
     * 修改轮播图
     * @param BroadcastPicture
     * @return
     */
    Integer updateBroadcastPicture(BroadcastPicture BroadcastPicture);


    Integer deleBroadcastPicture(Integer id);


    /**
     * 查询所有的轮播图
     * @return
     */
    Page findBroadcastPictureList(Integer pageNo,BroadcastPicture broadcastPicture);


    /**
     * 根据id查询对应的轮播图片
     * @param id
     * @return
     */
    BroadcastPicture findBroadcastPictureById(Integer id);
}
