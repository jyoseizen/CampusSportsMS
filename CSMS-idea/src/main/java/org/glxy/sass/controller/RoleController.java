package org.glxy.sass.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.glxy.sass.config.Result;
import org.glxy.sass.entity.Role;
import org.glxy.sass.entity.Users;
import org.glxy.sass.mapper.RoleMapper;
import org.glxy.sass.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Resource
    RoleMapper roleMapper;

    /**
     * 查询角色权限表
     *
     * @param PageNum
     * @param PageSize
//     * @param search
     * @return
     */
    @GetMapping("/search")
    public Result<?> selectEnroll(@RequestParam(defaultValue = "1") Integer PageNum,
                                  @RequestParam(defaultValue = "100") Integer PageSize,
                                  String usersStnumber, String usersName) {
//        LambdaQueryWrapper<Role> wrapper = Wrappers.<Role>lambdaQuery();
        Page<Role> rolePage = roleService.selectEnroll(new Page<>(PageNum, PageSize),usersStnumber,usersName);
        return Result.success(rolePage);
    }


    /**
     * 角色权限表新增
     *
     * @param role
     * @return
     */
    @PostMapping("/addR")
    public Result<?> addR(Role role) {
        roleService.addR(role);
        return Result.success("成功");
    }

    /**
     * 查询用户角色
     *
     * @return
     */
    @GetMapping("/selectValue")
    public Result<?> selectRoleValue(String usersStnumber) {
        String map = roleService.selectRoleValue(usersStnumber);
        System.out.println(map + "sss");
//        System.out.println(map.size()+"  "+map);
//        return roleService.selectRoleValue();
        return Result.success(map);
    }

    /**
     * 删除角色
     *
     * @param usersStnumber
     * @return
     */
    @GetMapping("/deleteBy")
    public Result<?> deleteByUsersStnumber(String usersStnumber) {
        roleService.deleteByUsersStnumber(usersStnumber);
        return Result.success();
    }

    /**
     * 修改角色
     *
     * @param usersStnumber
     * @return
     */
    @GetMapping("/updateBy")
    public Result<?> updateRoleValue(String usersStnumber, String roleValue) {
        roleService.updateRoleValue(usersStnumber, roleValue);
        return Result.success();
    }

}
