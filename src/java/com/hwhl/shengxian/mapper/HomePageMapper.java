package com.hwhl.shengxian.mapper;

import com.hwhl.shengxian.entity.Binding;
import com.hwhl.shengxian.entity.Information;
import com.hwhl.shengxian.entity.Notice;
import org.apache.ibatis.annotations.Param;

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
public interface HomePageMapper {

    /**
     * 查询系统公告
     * @param
     * @return
     */
    List<HashMap> findNoticeList();

    /**
     *电子协议
     * @return
     */
    List<HashMap> findAgreementList();

    /**
     * 获取系统公告/电子协议
     * @param id
     * @return
     */
    HashMap findNotice(@Param("id") Integer id);

    /**
     * 修改系统公告或协议内容
     * @param notice
     * @return
     */
    Integer updateNotice(Notice notice);

    /**
     * 异步获取三个不同端的版本号
     * @return
     */
    List<HashMap> version();

    /**
     * 修改版本号
     * @param id
     * @param version
     * @return
     */
    Integer updateVersion(@Param("id") Integer id, @Param("version") String version , @Param("create_time") Date create_time);


}
