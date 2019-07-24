package com.hwhl.shengxian.controller.system;

import com.hwhl.shengxian.entity.system.Menu;
import com.hwhl.shengxian.entity.system.Menus;
import com.hwhl.shengxian.service.system.AdminService;
import com.hwhl.shengxian.util.Global;
import com.hwhl.shengxian.util.Message;
import com.hwhl.shengxian.util.Page;
import com.hwhl.shengxian.util.annotation.SystemLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    /**
     * 登陸
     * @param account
     * @param password
     * @param verify
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping("/login")
    public String login(String account, String password, String verify, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        HttpSession session=request.getSession();
        String rand = (String) session.getAttribute("rand");
        //if (verify.toLowerCase().equals(rand)) {
        HashMap admin = adminService.findLogin(account, password);
        if (admin == null) {
                request.setAttribute("result", "账号或密码错误");
        }else if ((Integer)admin.get("is_del") == 0){//正常状态，登陆成功，写入cookie
            session.setAttribute("admin", admin);
            Cookie cookie = new Cookie("admin", admin.get("id").toString());
            cookie.setPath("/");
            response.addCookie(cookie);
            String username = (String) admin.get("username");
            session.setAttribute("admin",admin);
            session.setAttribute("USER_ID",username);//做日志处理时的用户
            session.setAttribute("pwd",password);//特别需要
            return "redirect:findAdminList.do";
        }else if ((Integer) admin.get("is_del") == 1) {//营业状态
            request.setAttribute("result", "当前用户已被冻结");
        }
        return "pages/jump";
    }

    /**
     * 查询全局页面左侧边栏目
     * @param request
     * @return
     */
    @RequestMapping("/findAdminList")
    public String findAdminList(HttpServletRequest request){
        HttpSession session=request.getSession();
        HashMap admin=(HashMap) session.getAttribute("admin");
        if(admin!=null) {
            List<Menu> adminLists = adminService.findAdminList((Integer)admin.get("admin_role_id"));
            request.setAttribute("adminLists", adminLists);

            Integer bid=null;
            Cookie[] cookies=request.getCookies();
            if (cookies != null){
                for (Cookie c : cookies) {
                    if ("admin".equals(c.getName())) {
                        bid = new Integer(c.getValue());
                    }
                }
                if (bid == null) {
                    request.setAttribute("result", "登录已过期，请重新登录");
                    return "pages/jump";
                } else {
                    return "pages/home";
                }
            }else {
                return "pages/jump";
            }
            //取出默认的哪个

          /*  boolean istrue=false;
            for(Menu listOne:adminLists){
                if(istrue){
                    break;
                }
                String id=listOne.getId()+"";
                if(role.indexOf(id)!=-1){
                    List<Menus> listTwos = listOne.getListTwo();
                    for(Menus listTwo:listTwos){
                        Integer id2=listTwo.getId();
                        if(role.indexOf(""+id2)!=-1){
                            String url = "../" + listTwo.getLink() + "?adminListId=" + id2;
                            istrue=true;
                            break;
                        }
                    }
                }
            }
            request.setAttribute("url",url);*/
        }else{
            request.setAttribute("result","登录已过期，请重新登录");
            return "pages/jump";
        }
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        request.setAttribute("result","登录已过期，请重新登录");
        return "pages/jump";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
        return "pages/jump";
    }

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
            boolean istrue=adminService.findAdminRole((Integer)admin.get("admin_role_id"),adminListId);
            if(istrue){
                return "redirect:findAdmin.do";
            }else{
                request.setAttribute("error","您没有权限访问该页面");
                return "pages/error";
            }
        }
    }

    /**
     * 分页查询管理员列表
     * @return
     */
    @RequestMapping("/findAdmin")
    public String findAdmin(Integer pageNo,Integer id,String account,String username,Integer adminRoleId,HttpServletRequest request){
        Page page=adminService.findAdminByPage(pageNo,id,account,username,adminRoleId);
        request.setAttribute("page",page);
        request.setAttribute("pageNo",pageNo);
        request.setAttribute("id",id);
        request.setAttribute("account",account);
        request.setAttribute("username",username);
        request.setAttribute("adminRoleId",adminRoleId);
        List<HashMap> adminRoles=adminService.findAllAdminRole();
        request.setAttribute("adminRoles",adminRoles);
        return "pages/system/adminList";
    }

    /**
     * 跳转到添加管理员界面
     * @param request
     * @return
     */
    @RequestMapping("/jumpSaveAdmin")
    public String jumpSaveAdmin(HttpServletRequest request){
        List<HashMap> adminRoles=adminService.findAllAdminRole();
        request.setAttribute("adminRoles",adminRoles);
        return "pages/system/saveAdmin";
    }

    /**
     * 添加管理员
     * @param account 账号
     * @param username 用户名
     * @param password 密码
     * @param adminRoleId 管理员角色id
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping("/saveAdmin")
    @ResponseBody
    public Message saveAdminUser(String account, String username, String password, Integer adminRoleId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Message message=Message.non();
        if(account==null||"".equals(account)){
            return message.code(Message.codeFailured).message("请输入账号");
        }
        if(username==null||"".equals(username)){
            return message.code(Message.codeFailured).message("请输入用户名");
        }
        if(password==null||"".equals(password)){
            return message.code(Message.codeFailured).message("请输入密码");
        }
        Integer count=adminService.saveAdmin(account,username,password,adminRoleId);
        if(count==null){
            return message.code(Message.codeFailured).message("该账号已存在，请重新输入");
        }else if(count==1){
            return message.code(Message.codeSuccessed).message("添加成功");
        }else{
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 查询管理员信息
     * @param id 管理员id
     * @return
     */
    @RequestMapping("/findAdminById")
    public String findAdminById(Integer id,HttpServletRequest request){
        List<HashMap> adminRoles=adminService.findAllAdminRole();
        request.setAttribute("adminRoles",adminRoles);
        HashMap admin=adminService.findAdminById(id);
        request.setAttribute("admin",admin);
        return "pages/system/updateAdmin";
    }

    /**
     * 修改管理员信息
     * @param id
     * @param account
     * @param username
     * @param password
     * @param adminRoleId
     * @return
     */
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public Message updateAdmin(Integer id,String account,String username,String password,Integer adminRoleId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Message message=Message.non();
        if(account==null||"".equals(account)){
            return message.code(Message.codeFailured).message("请输入账号");
        }
        if(username==null||"".equals(username)){
            return message.code(Message.codeFailured).message("请输入用户名");
        }
        Integer count=adminService.updateAdmin(id,account,username,password,adminRoleId);
        if(count==null){
            return message.code(Message.codeFailured).message("该账号已经存在，请重新输入");
        }else if(count==1){
            return message.code(Message.codeSuccessed).message("修改成功");
        }else {
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    @RequestMapping("/deleteAdmin")
    @ResponseBody
    public Message deleteAdmin(Integer id){
        Message message=Message.non();
        Integer count=adminService.deleteAdmin(id);
        if(count==1){
            return message.code(Message.codeSuccessed).message("删除成功");
        }else {
            return message.code(Message.codeFailured).message(Global.ERROR);
        }
    }
}
