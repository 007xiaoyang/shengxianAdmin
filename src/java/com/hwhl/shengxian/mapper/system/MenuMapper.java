package com.hwhl.shengxian.mapper.system;

import com.hwhl.shengxian.entity.system.Menu;
import com.hwhl.shengxian.entity.system.Menus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@Repository
public interface MenuMapper {

    /**
     * 首页权限菜单
     * @param role_id 角色id
     * @return
     */
    List<Menu> homeMenu(@Param("id") Integer role_id );

    /**
     * 首页二级权限菜单
     * @param role_id 角色id
     * @param menuid 菜单id
     * @return
     */
    List<Menus> homeTwoMenu(@Param("id") Integer role_id ,@Param("menuid") Integer menuid);

    /**
     * 查询后台一级列表
     * @return
     */
     List<Menu> findListOnes();

    /**
     * 查询后台的二级列表
     * @return
     */
     List<Menus> findListTwos(Integer fatherid);

    /**
     * 根据子级id查询父级id
     * @param id
     * @return
     */
     String findFatherById(Integer id);


    /**
     * 查询所有菜单集合，
     * @return
     */
    List<Menu> findMenuList(Menu menu);

    /**
     * 根据ID查询菜单
     * @param id
     * @return
     */
    Menu findMenu(Integer id);

    /**
     * 添加菜单
     * @param meun
     * @return
     */
    Integer addMenu(Menu meun);

    /**
     * 修改菜单
     * 删除菜单
     * @param meun
     * @return
     */
    Integer updateMenu(Menu meun);


}
