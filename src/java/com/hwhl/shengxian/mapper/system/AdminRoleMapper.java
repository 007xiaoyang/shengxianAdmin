package com.hwhl.shengxian.mapper.system;

import com.hwhl.shengxian.entity.system.AdminRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@Repository
public interface AdminRoleMapper {

    /**
     * 根据角色id查询角色权限
     * @param adminRoleId
     * @return
     */
    public String findRoleById(Integer adminRoleId);

    /**
     * 查询所有的管理员角色
     * @return
     */
    public List<HashMap> findAllAdminRole();

    /**
     * 查询管理员角色总条数
     * @param id
     * @param rolename
     * @return
     */
    public Integer findCount(@Param("id") Integer id, @Param("rolename") String rolename);

    /**
     * 分页查询管理员角色
     * @param startIndex
     * @param pageSize
     * @param id
     * @param rolename
     * @return
     */
    public List<AdminRole> findAdminRoleByPage(Integer startIndex, Integer pageSize, @Param("id") Integer id, @Param("rolename") String rolename);

    /**
     * 添加管理员角色
     * @param rolename
     * @param role
     * @param createtime
     * @return
     */
    public Integer addAdminRole(String rolename, String role, Long createtime);

    /**
     * 根据角色id查询角色信息
     * @param id
     * @return
     */
    public AdminRole findAdminRoleById(Integer id);

    /**
     * 修改管理员权限
     * @param id
     * @param rolename
     * @param role
     * @param updatetime
     * @return
     */
    public Integer updateAdminRole(Integer id, String rolename, String role, Long updatetime);

    /**
     * 删除管理员角色
     * @param id
     * @return
     */
    public Integer deleteAdminRole(Integer id);
}
