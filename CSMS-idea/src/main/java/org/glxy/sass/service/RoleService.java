package org.glxy.sass.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.Enroll;
import org.glxy.sass.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    void addR(Role role);

    /**
     * 获取用户权限
     * @param usersStnumber
     * @return
     */
    String selectRoleValue(String usersStnumber);

    /**
     * 删除角色
     * @param usersStnumber
     */
    void deleteByUsersStnumber(String usersStnumber);

    /**
     * 修改用户角色
     * @param usersStnumber
     */
    void updateRoleValue(String usersStnumber,String roleValue);

    /**
     * 根据学号，用户名查询报名列表
     *
     * @param page
     * @param usersStnumber
     * @param usersName
     * @return
     */
    Page<Role> selectEnroll(Page<Role> page,
                              @Param("usersStnumber") String usersStnumber,
                              @Param("usersName") String usersName);
}
