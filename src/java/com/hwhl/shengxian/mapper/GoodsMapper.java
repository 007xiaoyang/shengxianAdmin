package com.hwhl.shengxian.mapper;

import com.hwhl.shengxian.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Description: 商家DAO
 *
 * @Author: yang
 * @Date: 2018/6/22
 * @Version: 1.0
 */
public interface GoodsMapper {


    /**
     * 查询线上的商品列表总数
     * @param business_id 商家id
     * @param name 产品名称或编号
     * @return
     */
    Integer selectOnlineGoodsListCount(@Param("business_id") Integer business_id,@Param("store_name")String store_name ,@Param("name") String name);


    /**
     * 查询线上的商品列表
     * @param business_id 商家id
     * @param name 产品名称或编号
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<HashMap> selectOnlineGoodsList(@Param("business_id") Integer business_id ,@Param("store_name")String store_name ,@Param("name") String name,@Param("startIndex") Integer startIndex ,@Param("pageSize") Integer pageSize);


    /**
     * 查询线下的商品列表总数
     * @param business_id 商家id
     * @param name 产品名称或编号
     * @return
     */
    Integer selectUnderGoodsListCount(@Param("business_id") Integer business_id ,@Param("store_name")String store_name ,@Param("name") String name);


    /**
     * 查询线下的商品列表
     * @param business_id 商家id
     * @param name 产品名称或编号
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<HashMap> selectUnderGoodsList(@Param("business_id") Integer business_id ,@Param("store_name")String store_name ,@Param("name") String name,@Param("startIndex") Integer startIndex ,@Param("pageSize") Integer pageSize);

    /**
     * 产品下架
     * @param goods_id
     * @param status
     * @return
     */
    Integer updateGoodsStatus(@Param("id") Integer goods_id,@Param("status") Integer status);

    /**
     * 搜索商家
     * @return
     * @throws Exception
     */
    List<HashMap> selectBusienss();

    /**
     * 通过商家id获取商家名称
     * @param bid
     * @return
     * @throws Exception
     */
    HashMap selectBusienssById(@Param("id") Integer bid);


    /**
     * 上架的积分产品列表总数
     * @param business_id 商家id
     * @param name 产品名称或编号
     * @return
     */
    Integer upperIntegraGoodsListCount(@Param("business_id") Integer business_id ,@Param("store_name")String store_name ,@Param("name") String name);

    /**
     * 上架的积分产品列表
     * @param business_id 商家id
     * @param name 产品名称或编号
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<HashMap> upperIntegraGoodsLis(@Param("business_id") Integer business_id,@Param("store_name")String store_name ,@Param("name") String name,@Param("startIndex") Integer startIndex ,@Param("pageSize") Integer pageSize);


    /**
     * 下架的积分产品列表总数
     * @param business_id 商家id
     * @param name 产品名称或编号
     * @return
     */
    Integer lowerIntegraGoodsLisCount(@Param("business_id") Integer business_id ,@Param("store_name")String store_name ,@Param("name") String name);


    /**
     * 下架的积分产品列表
     * @param business_id 商家id
     * @param name 产品名称或编号
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<HashMap> lowerIntegraGoodsLis(@Param("business_id") Integer business_id ,@Param("store_name")String store_name,@Param("name") String name,@Param("startIndex") Integer startIndex ,@Param("pageSize") Integer pageSize);


    /**
     * 积分产品下架
     * @return
     */
    Integer updateIntegarGoodsStatus(@Param("id") Integer goods_id,@Param("status") Integer status);

    /**
     * 投诉处理总数
     * @param business_id
     * @param store_name
     * @return
     */
    Integer witnessesCount(@Param("business_id") Integer business_id ,@Param("store_name")String store_name);

    /**
     * 投诉处理
     * @param business_id
     * @param store_name
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<HashMap> witnesses(@Param("business_id") Integer business_id ,@Param("store_name")String store_name,@Param("startIndex") Integer startIndex ,@Param("pageSize") Integer pageSize);

    /**
     * 处理机举报
     * @param wid
     * @param state
     * @return
     */
    Integer updateWitnesses(@Param("id") Integer wid,@Param("state") Integer state);

}
