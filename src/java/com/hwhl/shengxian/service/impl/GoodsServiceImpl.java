package com.hwhl.shengxian.service.impl;

import com.hwhl.shengxian.entity.Goods;
import com.hwhl.shengxian.mapper.GoodsMapper;
import com.hwhl.shengxian.service.GoodsSerivce;
import com.hwhl.shengxian.util.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/22
 * @Version: 1.0
 */
@Service
public class GoodsServiceImpl implements GoodsSerivce {

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public Page selectOnlineGoodsList(Integer pageNo ,Integer business_id ,String store_name ,String name)throws Exception {
        int pageNum=1;
        if (pageNo !=null && pageNo != 0){
            pageNum=pageNo;
        }
        //分页查询线上的商品列表总数
        Integer totalCount = goodsMapper.selectOnlineGoodsListCount(business_id,store_name,name);
        Page page = new Page(pageNum,totalCount);
        List<HashMap> goodsList = goodsMapper.selectOnlineGoodsList(business_id,store_name,name,page.getStartIndex(), page.getPageSize());
        page.setRecords(goodsList);
        return page;
    }


    @Override
    public Page selectUnderGoodsList(Integer pageNo, Integer business_id,String store_name , String name)throws Exception {
        int pageNum=1;
        if (pageNo !=null && pageNo != 0){
            pageNum=pageNo;
        }
        //分页查询线上的商品列表总数
        Integer totalCount = goodsMapper.selectUnderGoodsListCount(business_id,store_name,name);
        Page page = new Page(pageNum,totalCount);
        List<HashMap> goodsList = goodsMapper.selectUnderGoodsList(business_id,store_name,name,page.getStartIndex(), page.getPageSize());
        page.setRecords(goodsList);
        return page;
    }

    @Override
    public Integer updateGoodsStatus(Integer goods_id, Integer status)throws Exception {
        return goodsMapper.updateGoodsStatus(goods_id,status);
    }

    @Override
    public List<HashMap> selectBusienss() throws Exception {
        return goodsMapper.selectBusienss();
    }

    @Override
    public HashMap selectBusienssById(Integer bid) throws Exception {
        return goodsMapper.selectBusienssById(bid);
    }

    @Override
    public Page upperIntegraGoodsLis(Integer pageNo, Integer business_id,String store_name, String name) throws Exception {
        int pageNum=1;
        if (pageNo !=null && pageNo != 0){
            pageNum=pageNo;
        }
        //分页查询线上的商品列表总数
        Integer totalCount = goodsMapper.upperIntegraGoodsListCount(business_id,store_name,name);
        Page page = new Page(pageNum,totalCount);
        List<HashMap> goodsList = goodsMapper.upperIntegraGoodsLis(business_id,store_name,name,page.getStartIndex(), page.getPageSize());
        page.setRecords(goodsList);
        return page;
    }

    @Override
    public Page lowerIntegraGoodsLis(Integer pageNo, Integer business_id,String store_name, String name)throws Exception {
        int pageNum=1;
        if (pageNo !=null && pageNo != 0){
            pageNum=pageNo;
        }
        //分页查询线上的商品列表总数
        Integer totalCount = goodsMapper.lowerIntegraGoodsLisCount(business_id,store_name,name);
        Page page = new Page(pageNum,totalCount);
        List<HashMap> goodsList = goodsMapper.lowerIntegraGoodsLis(business_id,store_name,name,page.getStartIndex(), page.getPageSize());
        page.setRecords(goodsList);
        return page;
    }

    @Override
    public Integer updateIntegarGoodsStatus(Integer goods_id, Integer status) {
        return goodsMapper.updateIntegarGoodsStatus(goods_id,status);
    }

    @Override
    public Page witnesses(Integer pageNo, Integer bid, String store_name)throws Exception {
        int pageNum=1;
        if (pageNo !=null && pageNo != 0){
            pageNum=pageNo;
        }
        Integer totalCount = goodsMapper.witnessesCount(bid,store_name);
        Page page = new Page(pageNum,totalCount);
        List<HashMap> hashMaps = goodsMapper.witnesses(bid,store_name,page.getStartIndex(),page.getPageSize());
        page.setRecords(hashMaps);
        return page;
    }

    @Override
    public Integer updateWitnesses(Integer wid, Integer state) throws Exception {
        return goodsMapper.updateWitnesses(wid,state);
    }
}
