package com.hwhl.shengxian.controller;

import cn.jpush.api.utils.StringUtils;
import com.hwhl.shengxian.entity.Binding;
import com.hwhl.shengxian.entity.Customer;
import com.hwhl.shengxian.entity.Business;
import com.hwhl.shengxian.mapper.CustomerMapper;
import com.hwhl.shengxian.service.CustomerSerivice;
import com.hwhl.shengxian.service.SellerService;
import com.hwhl.shengxian.util.Global;
import com.hwhl.shengxian.util.Message;
import com.hwhl.shengxian.util.Page;
import com.hwhl.shengxian.util.annotation.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

/**
 * Description: 客户
 *
 * @Author: yang
 * @Date: 2018/6/25
 * @Version: 1.0
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerSerivice customerSerivice;

    @Autowired
    private SellerService sellerService;

    /**
     * 客户列表
     * @return
     */
    @RequestMapping("/customer")
    @ResponseBody
    public Message customer(){
        Message message = Message.non();
        try {
            List<HashMap>  hashMaps = customerSerivice.customer();
            return message.code(Message.codeSuccessed).data(hashMaps).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }


    /**
     * 跳转到客户列表页面
     * @return
     */
    @RequestMapping("/JumpNewCustomerList")
    public String jumpCoustomer(HttpServletRequest request){
        return "pages/customer/newCustomerList";
    }

    /**
     * 分页客户列表
     * @param pageNo
     * @param phone
     * @return
     */
    @RequestMapping("/customerList")
    @ResponseBody
    public Message customerList(Integer pageNo,Integer cid ,String phone){
        Message message = Message.non();
        try {
            Page page = customerSerivice.jumpCoustomerList(pageNo,cid, phone);
            return message.code(Message.codeSuccessed).data(page).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 跳转到查询客户绑定多少个商家页面
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/jumpCustomerBindingBusiness")
    public String jumpCustomerBindingBusiness(Integer id,HttpServletRequest request){
        request.setAttribute("user_id",id);
        return "pages/customer/details";
    }

    /**
     * 查询客户账户
     * @param id
     * @return
     */
    @RequestMapping("/selectUserPhone")
    @ResponseBody
    public Message selectUserPhone(Integer id){
        Message message = Message.non();
        if (id == null ){
            return message.code(Message.codeFailured).message("请输入id");
        }
        try {
            String phone = customerSerivice.selectUserPhone(id);
            return message.code(Message.codeSuccessed).data(phone).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 查询客户绑定多少个商家
     * @param id
     * @return
     */
    @RequestMapping("/customerBindingBusiness")
    @ResponseBody
    public Message customerBindingBusiness(Integer pageNo,Integer id ,String name){
        Message message = Message.non();
        if (id == null || id == 0){
            return message.code(Message.codeFailured).message("请输入id");
        }
        try {
            Page page = customerSerivice.customerBindingBusiness(pageNo,id,name);
            return message.code(Message.codeSuccessed).data(page).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }


    /**
     * 删除客户
     * @param id
     * @return
     */
    @RequestMapping("/deleteCustomer")
    @ResponseBody
    public Message deleteCustomer(Integer id){
        Message message = Message.non();
        if (id == null || id == 0){
            return message.code(Message.codeFailured).message("请输入id");
        }
        try {
            Integer count = customerSerivice.deleteCustomer(id);
            if (count == null || count == 0) {
                return message.code(Message.codeFailured).message("删除失败");
            }
            return message.code(Message.codeSuccessed).message("删除成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 跳转到添加客户页面
     * @return
     */
    @RequestMapping("/jumpAddCustomer")
    public String jumpAddCustomer(){
        return "pages/customer/addCustomer";
    }

    /**
     * 添加客户
     * @return
     */
    @RequestMapping("/addCustomer")
    @ResponseBody
    public Message addCustomer(Customer customer){
        Message message = Message.non();
        try {
            Integer count = customerSerivice.addCustomer(customer);
            if (count == null || count == 0){
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
     * 重置客户密码
     * @param id
     * @return
     */
    @RequestMapping("/resetPassword")
    @ResponseBody
    public Message resetPassword(Integer id){
        Message message = Message.non();
        try {
            Integer count = customerSerivice.resetPassword(id);
            if (count == null || count == 0){
                return message.code(Message.codeFailured).message("重置失败");
            }
            return message.code(Message.codeSuccessed).message("重置成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 总平台推送消息
     * @param push_id 推送人id
     * @param title
     * @param content
     * @param type 0 商家，1客户
     * @return
     */
    @RequestMapping("/addPushContent")
    @ResponseBody
    public Message addPushContent(Integer push_id,String title,String content ,Integer type){
        Message message = Message.non();
        if (push_id == null ){
            return message.code(Message.codeFailured).message("请选择发送人id");
        }
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(content)){
            return message.code(Message.codeFailured).message("内容不能为空");
        }
        try {
            Integer count = customerSerivice.addPushContent(push_id, title, content, type);
            if (count == null || count == 0) {
                return message.code(Message.codeFailured).message("推送失败");
            }
            return message.code(Message.codeSuccessed).message("推送成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 禁用或启用
     * @param id
     * @param is_disable
     * @return
     */
    @RequestMapping("/updateDisable")
    @ResponseBody
    public Message updateDisable(Integer id,Integer is_disable){
        Message message = Message.non();
        if (id == null || id == 0){
            return message.code(Message.codeFailured).message("请输入id");
        }
        try {
            Integer count = customerSerivice.updateDisable(id, is_disable);
            if (count == null || count == 0){
                return message.code(Message.codeFailured).message("操作失败");
            }
            return message.code(Message.codeSuccessed).message("操作成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }


    /**
     * 修改客户登录多人或个人权限
     * @param id
     * @param power
     * @return
     */
    @RequestMapping("/updatePower")
    @ResponseBody
    public Message updatePower(Integer id ,Integer power){
        Message message = Message.non();
        if (id == null || power == null){
            return message.code(Message.codeFailured).message("不能为空");
        }
        try {
            Integer count = customerSerivice.updatePower(id, power);
            if (count == null || count == 0) {
                return message.code(Message.codeFailured).message("修改失败");
            }
            return message.code(Message.codeSuccessed).message("修改成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

}



