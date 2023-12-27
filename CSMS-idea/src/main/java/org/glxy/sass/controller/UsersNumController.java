package org.glxy.sass.controller;

import org.glxy.sass.entity.VO.UsersNum;
import org.glxy.sass.service.UsersNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usersNum")
public class UsersNumController {

    @Autowired
    UsersNumService usersNumService;

        /**
     * 统计报名人数（男，女，总  现统计用户表）
     * @return
     */
    @GetMapping("/selectSex")
    public UsersNum selectSex(){
        UsersNum usersNum = new UsersNum();
        usersNum = usersNumService.selectSex();
        System.out.println("users:"+usersNum);
        return usersNum;
    }
}
