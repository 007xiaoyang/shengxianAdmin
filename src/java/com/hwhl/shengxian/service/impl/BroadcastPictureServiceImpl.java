package com.hwhl.shengxian.service.impl;

import com.hwhl.shengxian.entity.BroadcastPicture;
import com.hwhl.shengxian.mapper.BroadcastPictureMapper;
import com.hwhl.shengxian.service.BroadcastPictureService;
import com.hwhl.shengxian.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/27
 * @Version: 1.0
 */
@Service
public class BroadcastPictureServiceImpl implements BroadcastPictureService {


    @Autowired
    private BroadcastPictureMapper broadcastPictureMapper;


    @Override
    public Integer addBroadcastPicture(BroadcastPicture broadcastPicture) {
        return broadcastPictureMapper.addBroadcastPicture(broadcastPicture);
    }

    @Override
    public Integer updateBroadcastPicture(BroadcastPicture broadcastPicture) {
        return broadcastPictureMapper.updateBroadcastPicture(broadcastPicture);
    }

    @Override
    public Integer deleBroadcastPicture(Integer id) {
        return broadcastPictureMapper.deleBroadcastPicture(id);
    }

    @Override
    public Page findBroadcastPictureList(Integer pageNo, BroadcastPicture broadcastPicture) {
        int pageNum=1;
        if (pageNo != null && pageNo != 0){
            pageNum=pageNo;
        }
        Integer totalCount = broadcastPictureMapper.findBroadcastPictureListCount(broadcastPicture);
        Page page = new Page(pageNum,totalCount);
        List<BroadcastPicture> broadcastPictureList = broadcastPictureMapper.findBroadcastPictureList(page.getStartIndex(), page.getPageSize(), broadcastPicture);
        page.setRecords(broadcastPictureList);
        return page;
    }

    @Override
    public BroadcastPicture findBroadcastPictureById(Integer id) {
        return broadcastPictureMapper.findBroadcastPictureById(id);
    }
}
