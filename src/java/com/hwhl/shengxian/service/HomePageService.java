package com.hwhl.shengxian.service;

import com.hwhl.shengxian.entity.Information;
import com.hwhl.shengxian.entity.Notice;
import com.hwhl.shengxian.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/27
 * @Version: 1.0
 */
public interface HomePageService {


    /**
     * 查询系统公告
     * @param
     * @return
     */
    List<HashMap> findNoticeList();

    /**
     *查询电子协议
     * @return
     */
    List<HashMap> findAgreementList();


    /**
     * 获取系统公告/电子协议
     * @param id
     * @return
     */
    HashMap findNotice( Integer id)throws Exception;

    /**
     * 修改系统公告或协议内容
     * @param notice
     * @return
     */
    Integer updateNotice(Notice notice)throws Exception;


    /**
     * 异步获取三个不同端的版本号
     * @return
     */
    List<HashMap> version()throws Exception;

    /**
     * 修改版本号
     * @param id
     * @param version
     * @return
     */
    Integer updateVersion(Integer id,String version)throws Exception;

}
