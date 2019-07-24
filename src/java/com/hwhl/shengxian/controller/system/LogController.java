package com.hwhl.shengxian.controller.system;

import com.hwhl.shengxian.entity.system.LogEntity;
import com.hwhl.shengxian.service.system.LogService;
import com.hwhl.shengxian.util.Message;
import com.hwhl.shengxian.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Description: 业务日志
 *
 * @Author: yang
 * @Date: 2018/7/26
 * @Version: 1.0
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Resource
    private LogService logService;
    /**
     * 跳转到操作业务日志页面
     * @return
     */
    @RequestMapping("/findLogList")
    public String jumpLogList(){
        return "pages/system/log";
    }

    /**
     * 查询操作业务日志列表
     * @param pageNo
     * @param name
     * @return
     */
    @RequestMapping("/logList")
    @ResponseBody
    public Message logList(Integer pageNo, String name){
        Message message = Message.non();
        Page page = logService.findLogList(pageNo,name);
        return message.code(Message.codeSuccessed).data(page).message("获取成功");
    }




}
