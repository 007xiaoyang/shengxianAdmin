package com.hwhl.shengxian.service;

import com.hwhl.shengxian.entity.Goods;
import com.hwhl.shengxian.util.Page;

import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/22
 * @Version: 1.0
 */
public interface GoodsSerivce {

    /**
     * 查询线上的商品列表
     * @param pageNo
     * @param business_id
     * @param name
     * @return
     */
    Page selectOnlineGoodsList(Integer pageNo,Integer business_id ,String store_name ,String name)throws Exception;

    /**
     * 查询线下的商品列表
     * @param pageNo
     * @param business_id
     * @param name
     * @return
     */
    Page selectUnderGoodsList(Integer pageNo,Integer business_id ,String store_name ,String name)throws Exception;

    /**
     * 产品下架
     * @param goods_id
     * @param status
     * @return
     */
    Integer updateGoodsStatus(Integer goods_id,Integer status)throws Exception;

    /**
     * 搜索商家
     * @return
     * @throws Exception
     */
    List<HashMap> selectBusienss()throws Exception;

    /**
     * 通过商家id获取商家名称
     * @param bid
     * @return
     * @throws Exception
     */
    HashMap selectBusienssById(Integer bid)throws Exception;


    /**
     * 上架的积分产品列表
     * @param pageNo
     * @param business_id
     * @param name
     * @return
     */
    Page upperIntegraGoodsLis(Integer pageNo,Integer business_id,String store_name  ,String name)throws Exception;


    /**
     * 下架的积分产品列表
     * @param pageNo
     * @param business_id
     * @param name
     * @return
     */
    Page lowerIntegraGoodsLis(Integer pageNo,Integer business_id ,String store_name ,String name)throws Exception;

    /**
     * 积分产品下架
     * @return
     */
    Integer updateIntegarGoodsStatus(Integer goods_id,Integer status);

    /**
     * 举报处理
     * @param pageNo
     * @param bid
     * @param store_name
     * @return
     */
    Page witnesses(Integer pageNo,Integer bid ,String store_name )throws Exception;

    /**
     * 处理机举报
     * @param wid
     * @param state
     * @return
     */
    Integer updateWitnesses(Integer wid,Integer state)throws Exception;

}
