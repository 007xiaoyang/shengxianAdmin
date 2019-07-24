package com.hwhl.shengxian.service.system;

import com.hwhl.shengxian.entity.system.LogEntity;
import com.hwhl.shengxian.util.Page;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/7/27
 * @Version: 1.0
 */
public interface LogService {

    /**
     * 添加操作日志信息
     * @param logEntity
     * @return
     */
    Integer saveLog(LogEntity logEntity);

    /**
     * 查询操作日志信息
     * @return
     */
    Page findLogList(Integer pageNo , String name);
}
