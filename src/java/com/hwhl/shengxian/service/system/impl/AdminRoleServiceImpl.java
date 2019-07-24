package com.hwhl.shengxian.service.system.impl;

import com.hwhl.shengxian.entity.system.AdminRole;
import com.hwhl.shengxian.entity.system.Menu;
import com.hwhl.shengxian.entity.system.Menus;
import com.hwhl.shengxian.mapper.system.MenuMapper;
import com.hwhl.shengxian.mapper.system.AdminMapper;
import com.hwhl.shengxian.mapper.system.AdminRoleMapper;
import com.hwhl.shengxian.service.system.AdminRoleService;
import com.hwhl.shengxian.util.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {
    @Resource
    private AdminRoleMapper adminRoleMapper;
    @Resource
    private MenuMapper adminListMapper;
    @Resource
    private AdminMapper adminMapper;

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
    public Page findAdminRoleByPage(Integer pageNo, Integer id, String rolename) {
        int pageNum=1;
        if(pageNo!=null&&pageNo!=0){
            pageNum=pageNo;
        }
        if(id==null||id==0){
            id=null;
        }
        if(rolename==null||"".equals(rolename)){
            rolename=null;
        }else{
            rolename="%"+rolename+"%";
        }
        int totalCount=adminRoleMapper.findCount(id,rolename);
        Page page=new Page(pageNum,totalCount);
        List<AdminRole> adminRoles=adminRoleMapper.findAdminRoleByPage(page.getStartIndex(),page.getPageSize(),id,rolename);
        page.setRecords(adminRoles);
        return page;
    }

    @Override
    public List<Menu> findAdminList() {
        List<Menu> listOnes=adminListMapper.findListOnes();
        //查出该一级下的二级
        for(Menu menu:listOnes){
            List<Menus> listTwos=adminListMapper.findListTwos( menu.getId());
            menu.setListTwo(listTwos);
        }
        return listOnes;
    }

    @Override
    public Integer addAdminRole(String rolename, Integer[] adminListIds) {
        String listId="";
        for(Integer adminListId:adminListIds){
            //查询列表的父级，如果父级相同，那么不添加，如果不存在，添加
            String fid=adminListMapper.findFatherById(adminListId);
            if(listId.indexOf(fid)==-1){
                listId+=fid+",";
            }
            listId+=adminListId+",";
        }
        //添加角色
        return adminRoleMapper.addAdminRole(rolename,listId,new Date().getTime());
    }

    @Override
    public AdminRole findAdminRoleById(Integer id) {
        return adminRoleMapper.findAdminRoleById(id);
    }

    @Override
    public Integer updateAdminRole(Integer id, String rolename, Integer[] adminListIds) {
        String listId="";
        for(Integer adminListId:adminListIds){
            //查询列表的父级，如果父级相同，那么不添加，如果不存在，添加
            String fid=adminListMapper.findFatherById(adminListId);
            if(listId.indexOf(fid)==-1){
                listId+=fid+",";
            }
            listId+=adminListId+",";
        }
        return adminRoleMapper.updateAdminRole(id,rolename,listId,new Date().getTime());
    }

    @Override
    public Integer deleteAdminRole(Integer id) {
        //删除前，查询该分类下是否有管理员，有管理员不让删除
        Integer count=adminMapper.findAdminCountById(id);
        if(count>0){
            return -1;
        }
        return adminRoleMapper.deleteAdminRole(id);
    }
}

