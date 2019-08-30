package com.hwhl.shengxian.mapper.system;

import com.hwhl.shengxian.entity.system.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@Repository
public interface AdminMapper {
    /**
     * 根据账号密码查询管理员
     * @param account
     * @param password
     * @return
     */
        public HashMap findAdmin(String account, String password);

    /**
     * 查询后台管理员总条数
     * @param id
     * @param account
     * @param username
     * @param adminRoleId
     * @return
     */
    public Integer findCount(@Param("id") Integer id, @Param("account") String account, @Param("username") String username, @Param("adminRoleId") Integer adminRoleId);

    /**
     * 分页查询后台管理员
     * @param startIndex
     * @param pageSize
     * @param id
     * @param account
     * @param username
     * @param adminRoleId
     * @return
     */
    public List<Admin> findAdminByPage(Integer startIndex, Integer pageSize, @Param("id") Integer id, @Param("account") String account, @Param("username") String username, @Param("adminRoleId") Integer adminRoleId);

    /**
     * 查询账号是否存在
     * @param account
     * @return
     */
    public Integer findIsAccount(String account);

    /**
     * 添加后台管理员
     * @param account
     * @param password
     * @param username
     * @param adminRoleId
     * @return
     */
    public Integer saveAdmin(String account, String password, String username, Integer adminRoleId);

    /**
     * 查询管理员
     * @param id
     * @return
     */
    public HashMap findAdminById(Integer id);

    /**
     * 查询账号是否存，除了他原来的
     * @param id
     * @param account
     * @return
     */
    public Integer findIsAccount2(Integer id, String account);

    /**
     * 修改管理员
     * @param id
     * @param account
     * @param username
     * @param password
     * @param adminRoleId
     * @return
     */
    public Integer updateAdmin(@Param("id") Integer id, @Param("account") String account, @Param("username") String username, @Param("password") String password, @Param("adminRoleId") Integer adminRoleId);

    /**
     * 删除管理员
     * @param id
     * @return
     */
    public Integer deleteAdmin(Integer id);

    /**
     * 记录登录时间
     * @param id
     * @param loginTime
     * @return
     */
    public Integer recordLoginTime(Integer id,Long loginTime);


    public Integer findAccountAndLoginTime(@Param("account") String account , @Param("failTime") String failTime );

    public Integer findFailLoginCount(@Param("account") String account , @Param("failTime") String failTime );

    /**
     * 记录登录失败次数
     * @param account
     * @param failTime
     * @return
     */
    public Integer addLoginFailCount(@Param("account") String account , @Param("failTime") Date failTime );


    /**
     * 修改登录失败次数
     * @return
     */
    public Integer updateLoginFailCount(@Param("id") Integer id );

    /**
     * 查询该角色id下管理员总数
     * @param rid
     * @return
     */
    public Integer findAdminCountById(Integer rid);
}
