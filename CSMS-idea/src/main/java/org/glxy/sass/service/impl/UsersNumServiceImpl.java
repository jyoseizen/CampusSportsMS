package org.glxy.sass.service.impl;

import org.glxy.sass.entity.VO.UsersNum;
import org.glxy.sass.mapper.UsersNumMapper;
import org.glxy.sass.service.UsersNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersNumServiceImpl implements UsersNumService {

    @Autowired
    UsersNumMapper usersNumMapper;
    /**
     * 统计报名人数（男，女，总  现统计用户表）
     * @return
     */
    @Override
    public UsersNum selectSex(){
        return usersNumMapper.selectSex();
    }
}

