package com.hwhl.shengxian.service;

import com.hwhl.shengxian.entity.Binding;
import com.hwhl.shengxian.entity.Customer;
import com.hwhl.shengxian.util.Page;
import org.apache.ibatis.annotations.Param;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/25
 * @Version: 1.0
 */
public interface CustomerSerivice {

    List<HashMap> customer();

    /**
     * 分页查询客户列表
     * @param pageNo
     * @param phone
     * @return
     */
    Page jumpCoustomerList(Integer pageNo ,Integer cid ,String phone);

    /**
     * 查询客户账户
     * @param id
     * @return
     */
    String selectUserPhone(Integer id);

    /**
     * 查询客户绑定多少个商家
     * @param cid
     * @return
     */
    Page customerBindingBusiness(Integer pageNo,Integer cid ,String name)throws Exception;

    /**
     * 删除客户
     * @param id
     * @return
     */
    Integer deleteCustomer(Integer id)throws Exception;

    /**
     * 添加客户
     * @param customer
     * @return
     * @throws Exception
     */
    Integer addCustomer(Customer customer)throws NullPointerException,Exception;

    /**
     * 重置客户密码
     * @param id
     * @return
     * @throws Exception
     */
    Integer resetPassword(Integer id)throws Exception;

    /**
     * 总平台推送消息
     * @param push_id
     * @param title
     * @param content
     * @param type
     * @return
     */
    Integer addPushContent(Integer push_id,String title,String content ,Integer type)throws Exception;

    /**
     * 启用或禁用
     * @param id
     * @param is_disable
     * @return
     */
    Integer updateDisable( Integer id ,Integer is_disable)throws Exception;

    /**
     * 单人或多人登录
     * @param id
     * @param power
     * @return
     * @throws Exception
     */
    Integer updatePower(Integer id,Integer power)throws Exception;

}
