package org.glxy.sass.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.glxy.sass.config.Result;
import org.glxy.sass.entity.Users;
import org.glxy.sass.service.RoleService;
import org.glxy.sass.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

/**
 * 登陆
 */
@Api
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UsersService usersService;

    @Autowired
    RoleService roleService;



    /**
     * 用户登陆接口
     *
     * @param usersStnumber
     * @param usersPwd
     * @return
     */
    @GetMapping("/doLogin")
    public Result<?> login(String usersStnumber, String usersPwd) {
        String ss = String.valueOf(usersService.loginSelect(usersStnumber, usersPwd));
//        List<Users> users = usersService.loginSelect(usersStnumber, usersPwd);
        System.out.println(usersStnumber);
        System.out.println(usersPwd);
        System.out.println("结果" + ss);
        //获取验证码
        if (ss == "[]") {
            return Result.error("账号或密码错误");
        }
        else{
            String loginId = roleService.selectRoleValue(usersStnumber);
            StpUtil.login(loginId);
            //发放登陆用户令牌
            return Result.success(StpUtil.getTokenValueByLoginId(loginId));
        }
    }

    /**
     * 获取当前会话id（测试id是否登陆）
     *
     * @return
     */
    @GetMapping("/ssss")
    public Result<?> login2() {
        String pwd = SaSecureUtil.md5("123456");
        return Result.success(pwd);
    }


}
