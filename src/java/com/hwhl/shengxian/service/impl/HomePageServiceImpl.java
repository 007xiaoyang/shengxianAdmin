package com.hwhl.shengxian.service.impl;

import com.hwhl.shengxian.entity.Information;
import com.hwhl.shengxian.entity.Notice;
import com.hwhl.shengxian.mapper.HomePageMapper;
import com.hwhl.shengxian.service.HomePageService;
import com.hwhl.shengxian.util.FormatDate;
import com.hwhl.shengxian.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/27
 * @Version: 1.0
 */
@Service
public class HomePageServiceImpl implements HomePageService {


    @Autowired
    private HomePageMapper homePageMapper;

    @Override
    public List<HashMap> findNoticeList() {
        return homePageMapper.findNoticeList();
    }

    @Override
    public List<HashMap> findAgreementList() {
        return homePageMapper.findAgreementList();
    }


    @Override
    public HashMap findNotice(Integer id)throws Exception {
        return homePageMapper.findNotice(id);
    }

    @Override
    public Integer updateNotice(Notice notice)throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateFromat = format.format(new Date());
        notice.setReleaseTime(new Date());
        return  homePageMapper.updateNotice(notice);
    }

    @Override
    public List<HashMap> version() throws Exception {
        return homePageMapper.version();
    }

    @Override
    public Integer updateVersion(Integer id, String version) throws Exception {
        return homePageMapper.updateVersion(id,version,new Date());
    }
}
