package com.hwhl.shengxian.service.system.impl;

import com.hwhl.shengxian.entity.system.LogEntity;
import com.hwhl.shengxian.mapper.system.LogMapper;
import com.hwhl.shengxian.service.system.LogService;
import com.hwhl.shengxian.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/7/27
 * @Version: 1.0
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public Integer saveLog(LogEntity logEntity) {
        return logMapper.saveLog(logEntity);
    }

    @Override
    public Page findLogList(Integer pageNo , String name) {
        int pageNum=1;
        if (pageNo != null && pageNo !=0){
            pageNum=pageNo;
        }

        Integer totalCount = logMapper.findLogListCount(name);
        Page page = new Page(pageNum,totalCount);
        List<LogEntity> logList = logMapper.findLogList(name ,page.getStartIndex(), page.getPageSize());
        page.setRecords(logList);
        return page;
    }
}
