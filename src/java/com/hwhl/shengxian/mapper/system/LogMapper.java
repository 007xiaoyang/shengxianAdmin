package com.hwhl.shengxian.mapper.system;

import com.hwhl.shengxian.entity.system.LogEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/7/27
 * @Version: 1.0
 */
public interface LogMapper {

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
    List<LogEntity> findLogList(@Param("name") String name ,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);

    Integer findLogListCount(@Param("name") String name);
}
