package com.hwhl.shengxian.mapper;

import com.hwhl.shengxian.entity.Binding;
import com.hwhl.shengxian.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/25
 * @Version: 1.0
 */
public interface CustomerMapper {

    List<HashMap> customer();

    /**
     * 客户列表总数
     * @param phone
     * @return
     */
    Integer jumpCoustomerListCount(@Param("id") Integer cid ,@Param("phone") String phone);

    /**
     * 客户列表
     * @param phone
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<HashMap> jumpCoustomerList(@Param("id") Integer cid ,@Param("phone") String phone,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);

    /**
     *查询客户绑定多少个商家总数
     * @param cid
     * @param name 商家名称或编号搜索
     * @return
     */
    Integer customerBindingBusinessCount(@Param("id") Integer cid,@Param("name") String name);

    /**
     * 查询客户账户
     * @param id
     * @return
     */
    String selectUserPhone(@Param("id") Integer id);

    /**
     * 查询客户绑定多少个商家
     * @param cid 客户id
     * @param name 商家名称或编号搜索
     * @return
     */
    List<HashMap> customerBindingBusiness(@Param("id") Integer cid,@Param("name") String name,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);

    /**
     * 删除客户
     * @param cid
     * @return
     */
    Integer deleteCustomer(Integer cid);

    /**
     * 查询手机号是否存在
     * @param phone
     * @return
     */
    Integer selectIdByPhone(@Param("phone") String phone);

    /**
     * 添加客户
     * @param customer
     * @return
     */
    Integer addCustomer(Customer customer);

    /**
     * 重置客户密码
     * @param id
     * @param pwd
     * @return
     */
    Integer resetPassword(@Param("id") Integer id,@Param("pwd") String pwd);

    /**
     * 总平台推送消息
     * @param push_id 推送人id 对应type
     * @param title 标题
     * @param message 内容
     * @param type 0商家，1客户
     * @return
     */
    Integer addPushContent(@Param("push_id") Integer push_id,@Param("title") String title,@Param("message") String message ,@Param("type") Integer type,@Param("create_time") Date create_time);

    /**
     * 启用或禁用
     * @param id
     * @param is_disable
     * @return
     */
    Integer updateDisable(@Param("id") Integer id ,@Param("is_disable") Integer is_disable);


    /**
     * 单人或多人登录
     * @param id
     * @param power
     * @return
     * @throws Exception
     */
    Integer updatePower(@Param("id") Integer id,@Param("power") Integer power);

}
