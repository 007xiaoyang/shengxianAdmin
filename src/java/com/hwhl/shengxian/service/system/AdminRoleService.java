package com.hwhl.shengxian.service.system;

import com.hwhl.shengxian.entity.system.AdminRole;
import com.hwhl.shengxian.entity.system.Menu;
import com.hwhl.shengxian.util.Page;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */
public interface AdminRoleService {

    /**
     * 查询用户权限
     * @param rid
     * @param adminListId
     * @return
     */
    public Boolean findAdminRole(Integer rid, Integer adminListId);

    /**
     * 分页查询管理员角色
     * @param pageNo
     * @param id
     * @param rolename
     * @return
     */
    public Page findAdminRoleByPage(Integer pageNo, Integer id, String rolename);

    /**
     * 查询管理员列表
     * @return
     */
    public List<Menu> findAdminList();

    /**
     * 添加管理员角色
     * @param rolename
     * @param adminListIds
     * @return
     */
    public Integer addAdminRole(String rolename, Integer[] adminListIds);

    /**
     * 根据角色id查询角色信息1
     * @param id
     * @return
     */
    public AdminRole findAdminRoleById(Integer id);

    /**
     * 修改管理员权限
     * @param id
     * @param rolename
     * @param adminListIds
     * @return
     */
    public Integer updateAdminRole(Integer id, String rolename, Integer[] adminListIds);

    /**
     * 删除管理员角色
     * @param id
     * @return
     */
    public Integer deleteAdminRole(Integer id);
}
