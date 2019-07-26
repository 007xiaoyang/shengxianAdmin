package com.hwhl.shengxian.mapper;

import com.hwhl.shengxian.entity.Binding;
import com.hwhl.shengxian.entity.Business;
import com.hwhl.shengxian.entity.Template;
import org.apache.ibatis.annotations.Param;

import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/23
 * @Version: 1.0
 */
public interface SellerMapper {

    /**
     * 查询账号是否注册过了
     * @param phone
     * @return
     */
    Integer selectIdByPhone(@Param("phone") String phone);

    /**
     * 查询最后一条编号
     * @return
     */
    String findBusinessLastNumber();

    /**
     * 添加商家
     * @return
     */
    Integer saveSeller(Business business);

    /**
     * 添加商家盘点
     * @param business_id
     * @return
     */
    Integer addClerk(@Param("business_id") Integer business_id);

    /**
     * 添加商家模板
     * @param template
     * @return
     */
    Integer addTemplate(Template template);

    /**
     * 添加模板2
     * @param business_id
     * @param title
     * @param type
     * @param one
     * @return
     */
    Integer addTemplateTwo( @Param("business_id") Integer business_id,@Param("title") String title ,@Param("type") Integer type ,@Param("one") String one );

    /**
     * 添加模板3
     * @param business_id
     * @param title
     * @param type
     * @param one
     * @return
     */
    Integer addTemplateThree( @Param("business_id") Integer business_id,@Param("title") String title ,@Param("type") Integer type ,@Param("one") String one );

    /**
     * 添加模板5
     * @param business_id
     * @param title
     * @param type
     * @param one
     * @return
     */
    Integer addTemplateFive( @Param("business_id") Integer business_id,@Param("title") String title ,@Param("type") Integer type ,@Param("one") String one );


    /**
     * 添加模板4
     * @param business_id
     * @param title
     * @param type
     * @param one
     * @return
     */
    Integer addTemplateFour( @Param("business_id") Integer business_id,@Param("title") String title ,@Param("type") Integer type ,@Param("one") String one );

    /**
     * 添加模板6
     * @param business_id
     * @param title
     * @param type
     * @param one
     * @return
     */
    Integer addTemplateSix( @Param("business_id") Integer business_id,@Param("title") String title ,@Param("type") Integer type ,@Param("one") String one );




    /**
     * 添加商家菜单方案
     * @param bid
     * @param name
     * @return
     */
    Integer addBusinessScheme(@Param("scheme_id") Integer scheme_id,@Param("bid") Integer bid,@Param("name") String name);

    /**
     * 审核商家
     * @param id
     * @return
     */
    Integer updateAdopt(@Param("id") Integer id);

    /**
     * 通过id查询手机号
     * @param id
     * @return
     */
    String findPhone(@Param("id") Integer id);

    /**
     * 查询手机号是存在
     * @param phone
     * @return
     */
    Integer phoneIsExist(@Param("phone") String phone);
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
     * 查询商家信息总数
     * @param
     * @return
     */
    Integer findSellerCount(@Param("business_id") Integer  business_id ,@Param("storeName") String storeName );

    /**
     *  查询商家信息
     * @param startIndex 每页开始记录的索引
     * @param pageSize 每页的页数
     * @param
     * @return
     */
    List<HashMap> findSellerList(@Param("business_id") Integer  business_id ,@Param("storeName") String storeName ,@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize );

    /**
     * 禁用店铺
     * @param id
     * @return
     */
    Integer updateBusines(@Param("id") Integer id );


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
    Integer addSellerSendInformation(@Param("business_id") Integer business_id,@Param("message") String message,@Param("create_time") Date create_time);

    /**
     * 通过服务商id查询绑定的客户列表
     * @param id
     * @param account
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<HashMap> selectCustomerListById(@Param("id") Integer id,@Param("name")String account,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);

    /**
     * 通过服务商id查询绑定的客户总数
     * @param id
     * @param name
     * @return
     */
    Integer selectCustomerListCountById(@Param("id") Integer id,@Param("name")String name);

    /**
     * 通过id查询账号
     * @param id
     * @return
     */
    String findSellerAccountById(@Param("id") Integer id);

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
    Integer upateDurationJurisdiction(@Param("id") Integer uid,@Param("jur") Integer jur);

    /**
     * 查询商家营业执照
     * @param bid 商家id
     * @return
     */
    List<HashMap> license(@Param("bid") Integer bid);

    /**
     * 上传营业执照
     * @param bid 商家id
     * @param img
     * @return
     */
    Integer addLicense(@Param("bid") Integer bid,@Param("img") String img);

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
    Integer updateBusinessSource(@Param("id") Integer id ,@Param("source") Integer source);

}
