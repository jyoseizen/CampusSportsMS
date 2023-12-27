package org.glxy.sass.service.impl;

import org.glxy.sass.entity.VO.UsersSex;
import org.glxy.sass.mapper.UsersSexMapper;
import org.glxy.sass.service.UsersSexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersSexServiceImpl implements UsersSexService {

    @Autowired
    UsersSexMapper usersSexMapper;

    /**
     * 用户信息导出表格
     * @return
     */
    @Override
    public List<UsersSex> listAll(){
        return usersSexMapper.listAll();
    }
}
