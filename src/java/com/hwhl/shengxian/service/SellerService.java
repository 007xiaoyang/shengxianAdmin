package com.hwhl.shengxian.service;

import com.hwhl.shengxian.entity.Binding;
import com.hwhl.shengxian.entity.Business;
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
 * @Date: 2018/6/23
 * @Version: 1.0
 */
public interface SellerService {

    /**
     * 添加商家
     * @param phone
     * @param store_name
     * @param name
     * @param illustrated
     * @param remarks
     * @return
     */
    Integer saveSeller(String phone,String store_name,String name,String illustrated,String remarks) throws NullPointerException,Exception;


    /**
     * 审核商家（通过默认8天使用期限）
     * @param id
     * @return
     */
    Integer updateAdopt(Integer id)throws Exception;

    /**
     * 修改商家信息
     * @param business
     * @return
     */
    Integer updateSeller(Business business);

    /**
     * 删除商家
     * @param id
     * @return
     */
    Integer deleteSeller(Integer id);


    /**
     * 分页查询商家列表信息
     * @param pageNo
     * @return
     */
    Page findSellerList(Integer pageNo,Integer  business_id , String storeName)throws Exception;

    /**
     * 根据ID查询商家信息
     * @param id
     * @return
     */
    Business findSellerById(Integer id);



    /**
     * 发送信息给商家
     * @param business_id
     * @param message
     * @return
     */
    Integer addSellerSendInformation(Integer business_id,String message);

    /**
     * 通过服务商id查询绑定的所有客户
     * @param id
     * @param pageNo
     * @param name
     * @return
     */
    Page selectCustomerListById(Integer id,Integer pageNo,String name);

    /**
     * 通过服务商id查询绑定的客户总数
     * @param id
     * @param account
     * @return
     */
    Integer selectCustomerListCountById(@Param("id") Integer id, @Param("account")String account);

    /**
     * 通过id查询账号
     * @param id
     * @return
     */
    String findSellerAccountById(Integer id);

    /**
     * 查询绑定表信息
     * @param id
     * @return
     */
    Binding selectBindingById(@Param("id") Integer id);


    /**
     * 修改使用期限权限
     * @param uid
     * @param jur
     * @return
     */
    Integer upateDurationJurisdiction(Integer uid,Integer jur);


    /**
     * 查询商家营业执照
     * @param id
     * @return
     */
    List<HashMap> license(Integer id);

    /**
     * 上传营业执照
     * @param bid
     * @param img
     * @return
     */
    Integer addLicense(Integer bid,String img);

    /**
     * 删除营业执照
     * @param id
     * @return
     */
    Integer deleteLicense(Integer id);

    /**
     * 修改商家来源
     * @param id
     * @param source
     * @return
     * @throws Exception
     */
    Integer updateBusinessSource(Integer id ,Integer source)throws Exception;

}
