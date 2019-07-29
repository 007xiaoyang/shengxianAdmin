package com.hwhl.shengxian.service.system;

import com.hwhl.shengxian.entity.system.Menu;
import com.hwhl.shengxian.util.Page;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
public interface AdminService {

    /**
     * 登陆
     * @param account
     * @param password
     * @return
     */
    public HashMap findLogin(String account, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 根据角色id查询角色权限
     * @param adminRoleId
     * @return
     */
    public String findRoleById(Integer adminRoleId);

    /**
     * 首页查询管理员列表
     * @return
     */
    public List<Menu> findAdminList(Integer role_id);

    /**
     * 判断当前用户登录成功后默认加载首页的权限
     * @param roleId
     * @return
     */
    Integer selectAdminLoginIsSellerMenuRole(Integer roleId);

    /**
     * 查询用户权限
     * @param rid
     * @param adminListId
     * @return
     */
    public Boolean findAdminRole(Integer rid, Integer adminListId);

    /**
     * 分页查询后台管理员
     * @param pageNo
     * @param id
     * @param account
     * @param username
     * @param adminRoleId
     * @return
     */
    public Page findAdminByPage(Integer pageNo, Integer id, String account, String username, Integer adminRoleId);

    /**
     * 查询所有的管理员角色
     * @return
     */
    public List<HashMap> findAllAdminRole();

    /**
     * 添加管理员
     * @param account
     * @param username
     * @param password
     * @param adminRoleId
     * @return
     */
    public Integer saveAdmin(String account, String username, String password, Integer adminRoleId) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 查询管理员详情
     * @param id
     * @return
     */
    public HashMap findAdminById(Integer id);

    /**
     * 修改管理员信息
     * @param id
     * @param account
     * @param username
     * @param password
     * @param adminRoleId
     * @return
     */
    public Integer updateAdmin(Integer id, String account, String username, String password, Integer adminRoleId) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 删除管理员
     * @param id
     * @return
     */
    public Integer deleteAdmin(Integer id);
}
