package com.hwhl.shengxian.controller;

import com.hwhl.shengxian.entity.Goods;
import com.hwhl.shengxian.service.GoodsSerivce;
import com.hwhl.shengxian.util.Global;
import com.hwhl.shengxian.util.Message;
import com.hwhl.shengxian.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/22
 * @Version: 1.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsSerivce goodsSerivce;

    /**
     * 跳转到线上的商品列表页面
     * @return
     */
    @RequestMapping("/JumpOnlineGoodsList")
    public String JumpOnlineGoodsList(){
        return "pages/goods/onlineGoodsList";
    }

    /**
     * 异步获取线上的商品列表
     * @param pageNo
     * @param business_id
     * @param name
     * @return
     */
    @RequestMapping("/selectOnlineGoodsList")
    @ResponseBody
    public Message selectOnlineGoodsList(Integer pageNo,Integer business_id,String store_name ,String name){
        Message message = Message.non();
        try {
            Page page = goodsSerivce.selectOnlineGoodsList(pageNo, business_id,store_name ,name);
            return message.code(Message.codeSuccessed).data(page).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 跳转到线下的商品列表页面
     * @return
     */
    @RequestMapping("/JumpsOfflineGoodsList")
    public String JumpsOfflineGoodsList(){
        return "pages/goods/offlineGoodsList";
    }

    /**
     * 异步获取线下的商品列表
     * @param pageNo
     * @param business_id
     * @param name
     * @return
     */
    @RequestMapping("/selectUnderGoodsList")
    @ResponseBody
    public Message selectUnderGoodsList(Integer pageNo,Integer business_id,String store_name ,String name){
        Message message = Message.non();
        try {
            Page page = goodsSerivce.selectUnderGoodsList(pageNo, business_id,store_name ,name);
            return message.code(Message.codeSuccessed).data(page).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     *修改产品状态
     * @param goods_id
     * @param status
     * @return
     */
    @RequestMapping("/updateGoodsStatus")
    @ResponseBody
    public Message updateGoodsStatus(Integer goods_id,Integer status){
        Message message = Message.non();
        try {
            Integer count = goodsSerivce.updateGoodsStatus(goods_id, status);
            if (count == null || count == 0){
                return message.code(Message.codeFailured).message("下架失败");
            }
            return message.code(Message.codeSuccessed).message("下架成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 搜索商家
     * @return
     */
    @RequestMapping("/selectBusienss")
    @ResponseBody
    public Message selectBusienss(){
        Message message = Message.non();
        try {
            List<HashMap> hashMaps = goodsSerivce.selectBusienss();
            return message.code(Message.codeSuccessed).data(hashMaps).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }
    /**
     * 通过商家id获取商家名称
     * @return
     */
    @RequestMapping("/selectBusienssById")
    @ResponseBody
    public Message selectBusienssById(Integer bid){
        Message message = Message.non();
        try {
            HashMap hashMap = goodsSerivce.selectBusienssById(bid);
            return message.code(Message.codeSuccessed).data(hashMap).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }


    /**
     * 跳转到上架的积分产品列表
     * @return
     */
    @RequestMapping("/JumpUpperIntegraGoodsList")
    public String JumpUpperIntegraGoodsList(){
        return "pages/goods/upperIntegralGoodsList";
    }

    /**
     * 上架的积分产品列表
     * @param pageNo
     * @param business_id
     * @param name
     * @return
     */
    @RequestMapping("/upperIntegraGoodsLis")
    @ResponseBody
    public Message upperIntegraGoodsLis(Integer pageNo,Integer business_id  ,String store_name ,String name){
        Message message = Message.non();
        try {
            Page page = goodsSerivce.upperIntegraGoodsLis(pageNo, business_id ,store_name, name);
            return message.code(Message.codeSuccessed).data(page).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }


    /**
     * 跳转到下架的积分产品列表
     * @return
     */
    @RequestMapping("/JumpLowerIntegraGoodsList")
    public String JumpLowerIntegraGoodsList(){
        return "pages/goods/lowerIntegralGoodsList";
    }

    /**
     * 下架的积分产品列表
     * @param pageNo
     * @param business_id
     * @param name
     * @return
     */
    @RequestMapping("/lowerIntegraGoodsLis")
    @ResponseBody
    public Message lowerIntegraGoodsLis(Integer pageNo,Integer business_id ,String store_name ,String name){
        Message message = Message.non();
        try {
            Page page = goodsSerivce.lowerIntegraGoodsLis(pageNo, business_id, store_name ,name);
            return message.code(Message.codeSuccessed).data(page).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }


    /**
     * 积分产品下架
     * @param goods_id
     * @param status
     * @return
     */
    @RequestMapping("/updateIntegarGoodsStatus")
    @ResponseBody
    public Message updateIntegarGoodsStatus(Integer goods_id,Integer status){
        Message message = Message.non();
        try {
            Integer count = goodsSerivce.updateIntegarGoodsStatus( goods_id,status);
            if (count == null || count == 0){
                return message.code(Message.codeFailured).message("下架失败");
            }
            return message.code(Message.codeSuccessed).message("下架成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 跳转到举报处理页面
     * @return
     */
    @RequestMapping("/JumpWitnesses")
    public String JumpWitnesses(){
        return "pages/goods/witnesses";
    }

    /**
     * 举报处理数据
     * @param pageNo
     * @param bid
     * @param store_name
     * @return
     */
    @RequestMapping("/witnesses")
    @ResponseBody
    public Message witnesses(Integer pageNo,Integer bid,String store_name){
        Message message = Message.non();
        try {
            Page page = goodsSerivce.witnesses(pageNo,bid,store_name);
            return message.code(Message.codeSuccessed).data(page).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 处理举报
     * @param wid
     * @param state
     * @return
     */
    @RequestMapping("/updateWitnesses")
    @ResponseBody
    public Message updateWitnesses(Integer wid,Integer state){
        Message message = Message.non();
        try {
            Integer count = goodsSerivce.updateWitnesses(wid,state);
            if (count ==  null || count == 0){
                return message.code(Message.codeFailured).message("处理失败");
            }
            return message.code(Message.codeSuccessed).message("处理成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

}
