package org.glxy.sass.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.Enroll;
import org.glxy.sass.entity.Role;
import org.glxy.sass.mapper.RoleMapper;
import org.glxy.sass.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    RoleMapper roleMapper;

    @Override
    public void addR(Role role) {
        roleMapper.addR(role);
    }

    /**
     * 获取用户权限
     *
     * @param usersStnumber
     * @return
     */
    @Override
    public String selectRoleValue(String usersStnumber) {
        return roleMapper.selectRoleValue(usersStnumber);
    }

    /**
     * 删除角色
     *
     * @param usersStnumber
     */
    @Override
    public void deleteByUsersStnumber(String usersStnumber) {
        roleMapper.deleteByUsersStnumber(usersStnumber);
    }

    /**
     * 修改用户角色
     *
     * @param usersStnumber
     */
    @Override
    public void updateRoleValue(String usersStnumber, String roleValue) {
        roleMapper.updateRoleValue(usersStnumber, roleValue);
    }

    /**
     * 根据学号，用户名查询报名列表
     *
     * @param page
     * @param usersStnumber
     * @param usersName
     * @return
     */
    @Override
    public Page<Role> selectEnroll(Page<Role> page,
                              @Param("usersStnumber") String usersStnumber,
                              @Param("usersName") String usersName){
        return roleMapper.selectEnroll(page, usersStnumber, usersName);
    }
}
