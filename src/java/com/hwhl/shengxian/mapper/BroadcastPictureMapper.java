package com.hwhl.shengxian.mapper;

import com.hwhl.shengxian.entity.BroadcastPicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/27
 * @Version: 1.0
 */
public interface BroadcastPictureMapper {

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


    Integer deleBroadcastPicture(@Param("id") Integer id);


    /**
     * 查询所有的轮播图
     * @return
     */
    List<BroadcastPicture> findBroadcastPictureList(@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize, @Param("broadcastPicture") BroadcastPicture broadcastPicture);

    Integer findBroadcastPictureListCount(@Param("broadcastPicture") BroadcastPicture broadcastPicture);

    /**
     * 根据id查询对应的轮播图片
     * @param id
     * @return
     */
    BroadcastPicture findBroadcastPictureById(Integer id);
}
