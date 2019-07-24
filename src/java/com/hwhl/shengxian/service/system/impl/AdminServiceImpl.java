package com.hwhl.shengxian.service.system.impl;

import com.hwhl.shengxian.entity.system.Admin;
import com.hwhl.shengxian.entity.system.Menu;
import com.hwhl.shengxian.entity.system.Menus;
import com.hwhl.shengxian.mapper.system.MenuMapper;
import com.hwhl.shengxian.mapper.system.AdminMapper;
import com.hwhl.shengxian.mapper.system.AdminRoleMapper;
import com.hwhl.shengxian.service.system.AdminService;
import com.hwhl.shengxian.util.Global;
import com.hwhl.shengxian.util.Page;
import com.hwhl.shengxian.util.PasswordMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@Service
public class AdminServiceImpl implements AdminService{
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public HashMap findLogin(String account, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        password = PasswordMD5.EncoderByMd5(password+ Global.PASSWORDKEY);
        HashMap admin=adminMapper.findAdmin(account,password);
        if(admin!=null){
            //记录登录时间
            adminMapper.recordLoginTime((Integer)admin.get("id"),new Date().getTime());
        }
        return admin;
    }

    @Override
    public String findRoleById(Integer adminRoleId) {
        return adminRoleMapper.findRoleById(adminRoleId);
    }

    @Override
    public List<Menu> findAdminList(Integer role_id) {

        //首页权限菜单
        List<Menu> listOnes=menuMapper.homeMenu(role_id);
        //查出该一级下的二级
        for(Menu hashMap:listOnes){
            List<Menus> listTwo = menuMapper.homeTwoMenu(role_id, hashMap.getId());
            hashMap.setListTwo(listTwo);
        }
        return listOnes;
    }

    @Override
    public Boolean findAdminRole(Integer rid, Integer adminListId) {
        String role=adminRoleMapper.findRoleById(rid);
        int num=role.indexOf(""+adminListId);
        if(num!=-1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Page findAdminByPage(Integer pageNo, Integer id, String account, String username, Integer adminRoleId) {
        int pageNum=1;
        if(pageNo!=null&&pageNo!=0){
            pageNum=pageNo;
        }
        if(id==null||id==0){
            id=null;
        }
        if(account==null||"".equals(account)){
            account=null;
        }else{
            account="%"+account+"%";
        }
        if(username==null||"".equals(username)){
            username=null;
        }else{
            username="%"+username+"%";
        }
        if(adminRoleId==null||adminRoleId==0){
            adminRoleId=null;
        }
        int totalCount=adminMapper.findCount(id,account,username,adminRoleId);
        Page page=new Page(pageNum,totalCount);
        List<Admin> admins=adminMapper.findAdminByPage(page.getStartIndex(),page.getPageSize(),id,account,username,adminRoleId);
        page.setRecords(admins);
        return page;
    }

    @Override
    public List<HashMap> findAllAdminRole() {
        return adminRoleMapper.findAllAdminRole();
    }

    @Override
    public Integer saveAdmin(String account, String username, String password, Integer adminRoleId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //查询该账号是否存在
        Integer isAccount=adminMapper.findIsAccount(account);
        if(isAccount>0){
            return null;
        }
        password=PasswordMD5.EncoderByMd5(password+Global.PASSWORDKEY);
        return adminMapper.saveAdmin(account,password,username,adminRoleId);
    }

    @Override
    public HashMap findAdminById(Integer id) {
        return adminMapper.findAdminById(id);
    }

    @Override
    public Integer updateAdmin(Integer id, String account, String username, String password, Integer adminRoleId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        password=password==null||"".equals(password)?null:PasswordMD5.EncoderByMd5(password+Global.PASSWORDKEY);
        //判断账号是否存在
        Integer isAccount=adminMapper.findIsAccount2(id,account);
        if(isAccount>0){
            return null;
        }
        return adminMapper.updateAdmin(id,account,username,password,adminRoleId);
    }

    @Override
    public Integer deleteAdmin(Integer id) {
        return adminMapper.deleteAdmin(id);
    }
}
