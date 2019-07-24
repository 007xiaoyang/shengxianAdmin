package com.hwhl.shengxian.controller.system;

import com.hwhl.shengxian.entity.system.Menu;
import com.hwhl.shengxian.mapper.system.MenuMapper;
import com.hwhl.shengxian.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Description: 首页
 *
 * @Author: yang
 * @Date: 2018/6/22
 * @Version: 1.0
 */
@Controller
public class MenuController {

    @Autowired
    private MenuMapper menuMapper;

    @RequestMapping("/home")
    public String home(){
        return "pages/seller/sellerList";
    }

    /**
     * 跳转到菜单管理页面列表
     * @return
     */
    @RequestMapping("/menulist")
    public String menulist( Menu menu , HttpServletRequest request){
        List<Menu> menuList = menuMapper.findMenuList(menu);
        request.setAttribute("menuList",menuList);
        return "pages/system/menulist";
    }


    /**
     * 跳转到添加菜单管理页面
     * @return
     */
    @RequestMapping("/JumpSaveMenu")
    public String JumpSaveMenu(){
        return "pages/system/saveMenu";
    }
    /**
     * 添加菜单管理
     * @param menu
     * @return
     */
    @RequestMapping("/addMenu")
    @ResponseBody
    public Message addMenu(Menu menu){
        Message message = Message.non();
        if(menu.getSort() == null || menu.getName() == null || menu.getLevel() == null){
            return message.code(Message.codeFailured).message("请填写参数!");
        }
        menu.setIsDele(0);
        Integer integer = menuMapper.addMenu(menu);
        if (integer == null || integer.equals(0)){
            return message.code(Message.codeFailured).message("添加失败!");
        }
        return message.code(Message.codeSuccessed).message("添加成功!");
    }


    /**
     * 跳转到修改菜单管理页面
     * @return
     */
    @RequestMapping("/JumpUpdateMenu")
    public String JumpUpdateMenu( Integer id ,HttpServletRequest request){
        Menu menu = menuMapper.findMenu(id);
        request.setAttribute("menu",menu);
        return "pages/system/updateMenu";
    }
    /**
     * 修改菜单管理
     * @param menu
     * @return
     */
    @RequestMapping("/updateMenu")
    @ResponseBody
    public Message updateMenu(Menu menu){
        Message message = Message.non();
        Integer integer = menuMapper.updateMenu(menu);
        if (integer == null || integer.equals(0)){
            return message.code(Message.codeFailured).message("修改失败!");
        }
        return message.code(Message.codeSuccessed).message("修改成功!");
    }

}
