package org.glxy.sass.config;

import cn.dev33.satoken.stp.StpInterface;
import org.glxy.sass.entity.Role;
import org.glxy.sass.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Component
public class StpInterfaceConfig implements StpInterface {
    @Autowired
    RoleService roleService;

    private static final String ADMIN = "admin";
    private static final String SUPERADMIN = "superAdmin";

    @Override
    public List<String> getPermissionList(Object o, String s) {
        List<String> list = new ArrayList<String>();
        list.add("*");
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<String>();
        String loginIdStr = (String) loginId;
        if (Objects.equals(loginIdStr, ADMIN)) {
            list.add("admin");
        } else if (Objects.equals(loginIdStr, SUPERADMIN)) {
            list.add("superAdmin");
        } else {
            list.add("user");
        }
//        list.add("user");
        return list;
    }
}
