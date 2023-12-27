package org.glxy.sass.controller;

import org.glxy.sass.entity.VO.UsersSex;
import org.glxy.sass.service.UsersSexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usersSex")
public class UsersSexController {

    @Autowired
    UsersSexService usersSexService;

    /**
     * 用户信息导出表格
     * @return
     */
    @GetMapping("/All")
    public List<UsersSex> listAll() {

        return usersSexService.listAll();
    }
}
