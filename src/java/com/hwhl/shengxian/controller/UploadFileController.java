package com.hwhl.shengxian.controller;


import com.hwhl.shengxian.util.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Description: 图片上传控制层
 *
 * @Author: yang
 * @Date: 2018-10-23
 * @Version: 1.0
 */
@Controller
@RequestMapping("/fileupload")
public class UploadFileController {

    static Log log = LogFactory.getLog(UploadFileController.class);

    private static final String path= "/usr/local/uploadFile/huotaiBGI";//上传服务器路径
    private static final String pathfind="https://www.bxy8888.com:8052/uploadFile/huotaiBGI";//返回前端图片路径
    /**
     * 单张图片上传
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Message upload(@RequestParam("file") MultipartFile file){
        Message message = Message.non();
        Long time = null;
        //判断文件是否为空
        if (!file.isEmpty()){
            try {
            File directory   = new File(path);//随便指定目录，可以是完整目录，也可以存在Tomcat根目录下
            if(!directory .exists()){
                directory .mkdir();
            }
            time = System.currentTimeMillis();
            // 文件保存路径
            String filePath  =  path +time+file.getOriginalFilename();
                // 转存文件
            file.transferTo(new File(filePath ));
            } catch (IOException e) {
                log.info(e);
            }
        }
        return message.code(Message.codeSuccessed).data(pathfind+time+file.getOriginalFilename()).message("获取成功");
    }

    /**
     * 多张图片上传
     * @param files
     * @return
     */
    @RequestMapping("/uploadList")
    @ResponseBody
    public Message uploadList(@RequestParam MultipartFile[] files){
        Message message = Message.non();
        //判断file数组不能为空并且长度大于0
        if (files != null && files.length>0){
            //循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                upload(file);
            }
        }
        return message.code(Message.codeSuccessed).message("获取成功");
    }



}
