package com.hwhl.shengxian.controller.system;

import com.hwhl.shengxian.entity.system.AdminRole;
import com.hwhl.shengxian.entity.system.Menu;
import com.hwhl.shengxian.service.system.AdminRoleService;
import com.hwhl.shengxian.util.Global;
import com.hwhl.shengxian.util.Message;
import com.hwhl.shengxian.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */
@Controller
@RequestMapping("/adminRole")
public class AdminRoleController {
    @Resource
    private AdminRoleService adminRoleService;

    /**
     * 查询用户是否有权限访问该页面
     * @param adminListId 管理员列表id
     * @param request
     * @return
     */
    @RequestMapping("/findAdminRole")
    public String findAdminRole(Integer adminListId,HttpServletRequest request){
        HttpSession session=request.getSession();
        HashMap admin=(HashMap) session.getAttribute("admin");
        if(admin==null){
            request.setAttribute("result","登录已过期，请重新登录");
            return "pages/jump";
        }else{
            boolean istrue=adminRoleService.findAdminRole((Integer)admin.get("admin_role_id"),adminListId);
            if(istrue){
                return "redirect:findAdminRoleByPage.do";
            }else{
                request.setAttribute("error","您没有权限访问该页面");
                return "pages/error";
            }
        }
    }

    /**
     * 分页查询管理员角色
     * @param pageNo
     * @param id
     * @param rolename
     * @param request
     * @return
     */
    @RequestMapping("/findAdminRoleByPage")
    public String findAdminRoleByPage(Integer pageNo,Integer id,String rolename,HttpServletRequest request){
        Page page=adminRoleService.findAdminRoleByPage(pageNo,id,rolename);
        request.setAttribute("page",page);
        request.setAttribute("pageNo",pageNo);
        request.setAttribute("id",id);
        request.setAttribute("rolename",rolename);
        return "pages/system/adminRoleList";
    }

    /**
     * 跳转到添加管理员角色页面
     * @param request
     * @return
     */
    @RequestMapping("/jumpSaveAdminRole")
    public String jumpSaveAdminRole(HttpServletRequest request){
        List<Menu> adminLists = adminRoleService.findAdminList();
        request.setAttribute("adminLists", adminLists);
        return "pages/system/addAdminRole";
    }

    /**
     * 添加管理员角色
     * @param rolename
     * @param adminListIds
     * @return
     */
    @RequestMapping("/addAdminRole")
    @ResponseBody
    public Message addAdminRole(String rolename, Integer[] adminListIds){
        Message message=Message.non();
        if(rolename==null||"".equals(rolename)){
            return message.code(Message.codeFailured).message("请输入角色名称");
        }
        Integer count=adminRoleService.addAdminRole(rolename,adminListIds);
        if(count==1){
            return message.code(Message.codeSuccessed).message("添加成功");
        }
        return message.code(Message.codeFailured).message(Global.ERROR);
    }

    /**
     * 根据角色id查询角色信息
     * @param id
     * @return
     */
    @RequestMapping("/findAdminRoleById")
    public String findAdminRoleById(Integer id,HttpServletRequest request){
        AdminRole adminRole=adminRoleService.findAdminRoleById(id);//查询当前用户角色
        String[] split = adminRole.getRole().split(",");
        adminRole.setRoles(split);
        request.setAttribute("adminRole",adminRole);
        List<Menu> adminLists = adminRoleService.findAdminList();
        request.setAttribute("adminLists", adminLists);
        return "pages/system/updateAdminRole";
    }

    /**
     * 修改管理员角色
     * @param id
     * @param rolename
     * @param adminListIds
     * @return
     */
    @RequestMapping("/updateAdminRole")
    @ResponseBody
    public Message updateAdminRole(Integer id,String rolename,Integer[] adminListIds){
        Message message=Message.non();
        if(rolename==null||"".equals(rolename)){
            return message.code(Message.codeFailured).message("请输入角色名称");
        }
        Integer count=adminRoleService.updateAdminRole(id,rolename,adminListIds);
        if(count==1){
            return message.code(Message.codeSuccessed).message("修改管理员权限成功");
        }
        return message.code(Message.codeFailured).message(Global.ERROR);
    }

    /**
     * 删除管理员角色
     * @param id
     * @return
     */
    @RequestMapping("/deleteAdminRole")
    @ResponseBody
    public Message deleteAdminRole(Integer id){
        Message message=Message.non();
        Integer count=adminRoleService.deleteAdminRole(id);
        if(count==1){
            return message.code(Message.codeSuccessed).message("删除成功");
        }else if(count==-1){
            return message.code(Message.codeFailured).message("该角色下有管理员，请先删除管理员");
        }
        return message.code(Message.codeFailured).message(Global.ERROR);
    }
}
