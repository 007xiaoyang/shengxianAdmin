package com.hwhl.shengxian.controller;

import com.alibaba.fastjson.JSONObject;
import com.hwhl.shengxian.controller.system.WebSocketUtil;
import com.hwhl.shengxian.entity.BroadcastPicture;
import com.hwhl.shengxian.entity.Information;
import com.hwhl.shengxian.entity.Notice;
import com.hwhl.shengxian.service.BroadcastPictureService;
import com.hwhl.shengxian.service.HomePageService;
import com.hwhl.shengxian.util.Global;
import com.hwhl.shengxian.util.Message;
import com.hwhl.shengxian.util.Page;
import com.hwhl.shengxian.util.annotation.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Description: 首页
 *
 * @Author: yang
 * @Date: 2018/6/26
 * @Version: 1.0
 */
@Controller
@RequestMapping("/homePage")
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    @Autowired
    private BroadcastPictureService broadcastPictureService;

    /**
     * 跳转到系统公告页面
     */
    @RequestMapping("/JumpSystemBulletin")
    public String JumpSystemBulletin(){
        return "pages/homePage/systemBulletin";
    }

    /**
     * 异步获取系统公告
     * @return
     */
    @RequestMapping("/systemBulletin")
    @ResponseBody
    public Message systemBulletin(){
        Message message = Message.non();
        try {
            List<HashMap> hashMaps = homePageService.findNoticeList();
            return message.code(Message.codeSuccessed).data(hashMaps).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 跳转到电子协议页面
     * @param
     * @return
     */
    @RequestMapping("/JumpAgreementList")
    public String JumpAgreementList(){
        return "/pages/homePage/agreementList";
    }

    /**
     * 异步获取电子协议
     * @return
     */
    @RequestMapping("/agreement")
    @ResponseBody
    public Message agreement(){
        Message message = Message.non();
        try {
            List<HashMap> hashMaps = homePageService.findAgreementList();
            return message.code(Message.codeSuccessed).data(hashMaps).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 跳转到修改系统公告页面
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/JumpUpdateSystemBulletin")
    public String JumpUpdateSystemBulletin(Integer id ,HttpServletRequest request){
        request.setAttribute("id",id);
        return "pages/homePage/updateSystemBulletin";
    }

    /**
     * 跳转到修改电子协议页面
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/JumpUpdateAgreement")
    public String JumpUpdateAgreement(Integer id ,HttpServletRequest request){
        request.setAttribute("id",id);
        return "pages/homePage/updateAgreement";
    }

    /**
     * 通过id查询信息
     * @return
     */
    @RequestMapping("/findNotice")
    @ResponseBody
    public Message findNotice(Integer id){
        Message message =Message.non();
        try {
            HashMap hashMap = homePageService.findNotice(id);
            return message.code(Message.codeSuccessed).data(hashMap).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 修改系统公告/电子协议
     * @param notice
     * @return
     */
    @RequestMapping("/UpdateSystemBulletin")
    @ResponseBody
    @SystemLog(module = "系统管理",methods = "修改系统公告/电子协议")
    public Message UpdateSystemBulletin(Notice notice){
        Message message = Message.non();
        try {
            Integer integer = homePageService.updateNotice(notice);
            if (integer == null || integer == 0) {
                return message.code(Message.codeFailured).message("修改失败！");
            }
           /* WebSocketUtil ws = new WebSocketUtil();
            JSONObject jo = new JSONObject();
            jo.put("message", notice.getNoticeContent());
            jo.put("token", "All");//推送给所有商家
            try {
                ws.onMessage(jo.toString());
                System.out.println("开始测试");
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            return message.code(Message.codeSuccessed).message("修改成功！");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }






    /**
     * 跳转到轮播图页面
     * @param pageNo
     * @param request
     * @return
     */
    @RequestMapping("/JumpBackgroundImageList")
    public String JumpBackgroundImage(Integer pageNo ,BroadcastPicture broadcastPicture,HttpServletRequest request){
        Page page = broadcastPictureService.findBroadcastPictureList(pageNo, broadcastPicture);
        request.setAttribute("page",page);
        return "pages/homePage/backgroundImageList";
    }

    /**
     * 跳转到添加轮播图页面
     * @return
     */
    @RequestMapping("/JumpAddBackgroundImage")
    public String JumpAddBackgroundImage(){
        return "pages/homePage/addBackgroundImage";
    }

    /**
     * 添加轮播图
     * @return
     */
    @RequestMapping("/addBackgroundImage")
    @ResponseBody
    @SystemLog(module = "系统管理",methods = "添加轮播图")
    public Message addBackgroundImage(BroadcastPicture broadcastPicture){
        Message messgae = Message.non();
        if (broadcastPicture.getFigure()==null || broadcastPicture.getFigure().equals("")){
            return messgae.code(Message.codeFailured).message("图片不能为空哦!");
        }
        if (broadcastPicture.getDistinguish().equals("0")){
            return messgae.code(Message.codeFailured).message("请指定轮播图的所属!");
        }else if (broadcastPicture.getDistinguish().equals("1")) {
            broadcastPicture.setDistinguish("服务商");
        }else {
            broadcastPicture.setDistinguish("客户端");
        }
        broadcastPictureService.addBroadcastPicture(broadcastPicture);
        return messgae.code(Message.codeSuccessed).message("添加成功!");
    }


    /**
     * 跳转到修改轮播图页面
     * @return
     */
    @RequestMapping("/JumpUpdateBackgroundImage")
    public String JumpUpdateBackgroundImage(Integer id ,HttpServletRequest request){
        BroadcastPicture broadcastPicture = broadcastPictureService.findBroadcastPictureById(id);
        request.setAttribute("broadcastPicture",broadcastPicture);
        return "pages/homePage/updateBackgroundImage";
    }

    /**
     * 修改轮播图
     * @return
     */
    @RequestMapping("/updateBackgroundImage")
    @ResponseBody
    @SystemLog(module = "系统管理",methods = "修改轮播图")
    public Message updateBackgroundImage(BroadcastPicture broadcastPicture){
        Message messgae = Message.non();
        Integer integer = broadcastPictureService.updateBroadcastPicture(broadcastPicture);
        if (integer== null || integer == 0){
            return messgae.code(Message.codeFailured).message("修改失败!");
        }
        return messgae.code(Message.codeSuccessed).message("修改成功!");
    }

    /**
     * 删除轮播图
     * @param id
     * @return
     */
    @RequestMapping("/deleBackgroundImage")
    @ResponseBody
    @SystemLog(module = "系统管理",methods = "删除轮播图")
    public Message deleBackgroundImage(Integer id){
        Message messgae = Message.non();
        Integer integer = broadcastPictureService.deleBroadcastPicture(id);
        if (integer== null || integer == 0){
            return messgae.code(Message.codeFailured).message("删除失败!");
        }
        return messgae.code(Message.codeSuccessed).message("删除成功!");
    }


    /**
     * 跳转到版本号页面
     * @return
     */
    @RequestMapping("/jumpVersion")
    public String jumpVersion(){
        return "pages/homePage/version";
    }

    /**
     * 异步获取三个不同端的版本号
     * @return
     */
    @RequestMapping("/version")
    @ResponseBody
    public Message version(){
        Message message = Message.non();
        try {
            List<HashMap> hashMaps = homePageService.version();
            return message.code(Message.codeSuccessed).data(hashMaps).message("获取成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }


    /**
     * 修改版本号
     * @param id
     * @param version
     * @return
     */
    @RequestMapping("/updateVersion")
    @ResponseBody
    public Message updateVersion(Integer id,String version){
        Message message = Message.non();
        try {
            if (id == null || id == 0){
                return message.code(Message.codeFailured).message("输入id");
            }
           Integer count = homePageService.updateVersion(id,version);
           if (count == null || count == 0){
               return message.code(Message.codeFailured).message("修改失败");
           }
            return message.code(Message.codeSuccessed).message("修改成功");
        }catch (Exception e){
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }


}
