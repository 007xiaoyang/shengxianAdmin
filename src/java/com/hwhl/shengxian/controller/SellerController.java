package com.hwhl.shengxian.controller;

import com.hwhl.shengxian.entity.Binding;
import com.hwhl.shengxian.entity.Business;
import com.hwhl.shengxian.service.SellerService;
import com.hwhl.shengxian.util.*;
import com.hwhl.shengxian.util.annotation.SystemLog;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
@Controller
@RequestMapping("/seller")
public class SellerController {


    @Autowired
    private SellerService sellerService;


    /**
     * 获取验证码
     * @param phone
     * @return
     */
    @RequestMapping("/sendSms")
    @ResponseBody
    public Message sendSms(String phone){
        Message message = Message.non();
        if (phone == null || "".equals(phone)){
            return message.code(Message.codeFailured).message("请输入手机号");
        }
        try {
            Integer code = Sendsms.sendSMS(phone);
            if (code == null){
                return message.code(Message.codeFailured).message("获取验证码失败");
            }
            return message.code(Message.codeSuccessed).data(code).message("获取验证码成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return message.code(Message.codeFailured).message(Global.ERROR);
    }


    /**
     * 添加商家（人工添加）
     * @param phone
     * @param store_name
     * @param name
     * @param illustrated
     * @param remarks
     * @return
     */
    @RequestMapping("/saveSeller")
    @ResponseBody
    public Message saveSeller(String phone,String store_name,String name,String illustrated,String remarks){
        Message message = Message.non();
        try {
            Integer count = sellerService.saveSeller(phone, store_name, name, illustrated, remarks);
            if (count == null || count == 0) {
                return message.code(Message.codeFailured).message("添加失败");
            }
            return message.code(Message.codeSuccessed).message("添加成功");
        }catch (NullPointerException e){
            return message.code(Message.codeFailured).message(e.getMessage());
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 审核商家
     * @param id
     * @param
     * @return
     */
    @RequestMapping("/adopt")
    @ResponseBody
    public Message adopt(Integer id){
        Message message = Message.non();
        if (id == null ){
            return message.code(Message.codeFailured).message("参数有误");
        }
        try {
            Integer integer = sellerService.updateAdopt(id);
            if (integer == null) {
                return message.code(Message.codeFailured).message("操作失败!");
            }
            return message.code(Message.codeSuccessed).message("操作成功!");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }


    /**
     * 跳转到商家列表页面
     * @return
     */
    @RequestMapping("/JumpSellerList")
    public String JumpSellerList( HttpServletRequest request){
        return "pages/seller/sellerList";
    }

    /**
     * 异步获取商家列表
     * @param pageNo
     * @param storeName 商家名称条件查询
     * @param
     * @param
     * @return
     */
    @RequestMapping("/sellerList")
    @ResponseBody
    public Message sellerList(Integer pageNo,Integer  business_id , String storeName){
        Message message = Message.non();
        try {
            Page page = sellerService.findSellerList(pageNo,business_id, storeName);
            return message.code(Message.codeSuccessed).data(page).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }


    /**
     * 跳转到添加商家页面
     * @return
     */
    @RequestMapping("/JumpAddSeller")
    public String JumpAddSeller(){
        return "pages/seller/addSeller";
    }



    /**
     * 跳转到修改商家信息页面
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/JumpUpdateSeller")
    public String JumpUpdateSeller(Integer id,HttpServletRequest request){
        Business seller = sellerService.findSellerById(id);
        request.setAttribute("seller",seller);
        return "pages/seller/updateSeller";
    }

    /**
     * 修改商家信息
     * @param
     * @return
     */
    @RequestMapping("/updateSeller")
    @ResponseBody
    @SystemLog(module = "服务商管理",methods = "修改服务商资料")
    public Message updateSeller( Business business){
        Message message = Message.non();
        Integer integer = sellerService.updateSeller(business);
        if (integer == null || integer == 0){
           return message.code(Message.codeFailured).message("修改失败！");
        }else if (integer == -2){
            return message.code(Message.codeFailured).message("手机号已存在！");
        }
       return message.code(Message.codeSuccessed).message("修改成功！");
    }

    /**
     * 重置商家密码
     * @param id
     * @return
     */
    @RequestMapping("/resetPassword")
    @ResponseBody
    @SystemLog(module = "服务商管理",methods = "重置服务商密码")
    public Message resetPassword(Integer id){
        Message message = Message.non();
        if (id == null){
            return message.code(Message.codeFailured).message("获取参数失败！");
        }
        Business seller = new Business();
        try {
            String pwd = PasswordMD5.EncoderByMd5("123456" + Global.PASSWORDKEY);
            seller.setId(id);
            seller.setPassword(pwd);
            Integer integer = sellerService.updateSeller(seller);
            if (integer != null || integer != 0){
                return message.code(Message.codeSuccessed).message("操作成功!");
            }
            return message.code(Message.codeFailured).message("操作失败！");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message.code(Message.codeFailured).message("操作失败！");

    }

    /**
     * 启用商家
     * @param id
     * @param
     * @return
     */
    @RequestMapping("/enableSeller")
    @ResponseBody
    public Message enableSeller(Integer id){
        Message message = Message.non();
        if (id == null ){
          return message.code(Message.codeFailured).message("参数有误");
        }
        Business business = new Business();
        business.setId(id);
        business.setIs_disable(0);
        Integer integer = sellerService.updateSeller(business);
        if (integer == null){
            return message.code(Message.codeFailured).message("操作失败!");
        }

        return message.code(Message.codeSuccessed).message("操作成功!");
    }



    /**
     * 禁用商家
     * @param id
     * @param
     * @return
     */
    @RequestMapping("/disableSeller")
    @ResponseBody
    public Message disableSeller(Integer id){
        Message message = Message.non();
        if (id == null ){
            return message.code(Message.codeFailured).message("参数有误");
        }
        Business seller = new Business();
        seller.setId(id);
        seller.setIs_disable(1);
        sellerService.updateSeller(seller);
        return message.code(Message.codeSuccessed).message("操作成功!");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/deleteSeller")
    @ResponseBody
    @SystemLog(module = "服务商管理",methods = "删除服务商")
    public Message deleteSeller(Integer id){
        Message message = Message.non();
        if (id == null){
            return message.code(Message.codeFailured).message("参数有误");
        }
        Integer integer = sellerService.deleteSeller(id);
        if (integer == null){
            return message.code(Message.codeFailured).message("操作失败!");
        }
        return message.code(Message.codeSuccessed).message("操作成功!");

    }


    /**
     * 设置单人登录
     * @param business
     * @return
     */
    @RequestMapping("/manyLoginJurisdiction")
    @ResponseBody
    public Message manyLoginJurisdiction(Business business){
        Message message = Message.non();
        if (business == null){
            return message.code(Message.codeFailured).message("参数有误");
        }
        Integer code = sellerService.updateSeller(business);
        if (code == null){
            return message.code(Message.codeFailured).message("操作失败!");
        }
        return message.code(Message.codeSuccessed).message("操作成功!");
    }

    /**
     * 设置多人登录
     * @param business
     * @return
     */
    @RequestMapping("/singleLoginJurisdiction")
    @ResponseBody
    public Message singleLoginJurisdiction(Business business){
        Message message = Message.non();
        if (business == null){
            return message.code(Message.codeFailured).message("参数有误");
        }
        Integer integer = sellerService.updateSeller(business);
        if (integer != null){
            return message.code(Message.codeSuccessed).message("操作成功!");
        }
        return message.code(Message.codeFailured).message("操作失败!");
    }

    /**
     * 发送信息给商家
     * @param reason
     * @param sellerId
     * @return
     */
    @RequestMapping("/addSellerSendInformation")
    @ResponseBody
    public Message addSellerSendInformation(Integer sellerId,String reason){
        Message message = Message.non();
        if(sellerId == null || sellerId == 0){
            return message.code(Message.codeFailured).message("请输入商家id");
        }
        if (reason == null || "".equals(reason)){
            return message.code(Message.codeFailured).message("请输入原因");
        }
        Integer count = sellerService.addSellerSendInformation(sellerId,reason);
        if (count == null){
            return message.code(Message.codeFailured).message("发送信息失败");
        }
        return message.code(Message.codeSuccessed).message("发送信息成功");
    }

    /**
     * 跳转到服务商绑定所有客户的列表页面
     * @param id 服务商id
     * @param request
     * @return
     */
    @RequestMapping("/jumpDetails")
    public String JumpDetails(Integer id ,HttpServletRequest request){
        request.setAttribute("id" ,id);
        return "pages/seller/details";
    }

    /**
     * 服务商绑定所有客户的列表
     * @param id 服务商id
     * @return
     */
    @RequestMapping("/Details")
    @ResponseBody
    public Message Details(Integer id,Integer pageNo,String name){
        Message message = Message.non();
        Page page = sellerService.selectCustomerListById(id, pageNo, name);
        return message.code(Message.codeSuccessed).data(page).message("获取成功");
    }


    /**
     * 绑定客户详情
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/bindingdDetails")
    public String detailsInfo(Integer id,HttpServletRequest request){
        Binding binding = sellerService.selectBindingById(id);
        request.setAttribute("binding",binding);
        return "pages/seller/bindingdDetails";
    }

    /**
     * 修改使用期限权限
     * @param uid
     * @param jur
     * @return
     */
    @RequestMapping("/upateDurationJurisdiction")
    @ResponseBody
    public Message upateDurationJurisdiction(Integer uid,Integer jur){
        Message message = Message.non();
        Integer count = sellerService.upateDurationJurisdiction(uid,jur);
        if (count == null || count ==0){
            return message.code(Message.codeFailured).message("修改失败");
        }
        return message.code(Message.codeSuccessed).message("修改成功");
    }

    /**
     * 跳转到营业执照页面
     * @param id
     * @return
     */
    @RequestMapping("/jumpLicense")
    public String license(Integer id ,HttpServletRequest request){
        request.setAttribute("id",id);
        return "pages/seller/license";
    }

    /**
     * 查询营业执照
     * @param id
     * @return
     */
    @RequestMapping("/license")
    @ResponseBody
    public Message  license(Integer id ){
        Message message = Message.non();
        List<HashMap> license = sellerService.license(id);
        return message.code(Message.codeSuccessed).data(license).message("获取成功");
    }
    /**
     * 上传营业执照
     * @param img
     * @return
     */
    @RequestMapping("/addLicense")
    @ResponseBody
    public Message addLicense(Integer bid ,String img ){
        Message message = Message.non();
        if (bid == null || img== null || img.equals("")){
            return message.code(Message.codeFailured).message("输入参数");
        }
        Integer count = sellerService.addLicense(bid,img);
        if (count == null || count == 0){
            return message.code(Message.codeFailured).message("上传失败");
        }
        return message.code(Message.codeSuccessed).message("上传成功");
    }

    /**
     * 上传营业执照
     * @param id
     * @return
     */
    @RequestMapping("/deleteLicense")
    @ResponseBody
    public Message deleteLicense(Integer id){
        Message message = Message.non();
        Integer count = sellerService.deleteLicense(id);
        if (count == null || count == 0){
            return message.code(Message.codeFailured).message("删除失败");
        }
        return message.code(Message.codeSuccessed).message("删除成功");
    }


    /**
     * 修改商家来源
     * @param id
     * @param source
     * @return
     */
    @RequestMapping("/updateBusinessSource")
    @ResponseBody
    public Message updateBusinessSource(Integer id,Integer source){
        Message message = Message.non();
        if (id == null || source == null){
            return message.code(Message.codeFailured).message("参数不能为空");
        }
        try {
            Integer count = sellerService.updateBusinessSource(id,source);
            if (count == null || count == 0){
                return message.code(Message.codeFailured).message("修改失败");
            }
            return message.code(Message.codeSuccessed).message("修改成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

}
