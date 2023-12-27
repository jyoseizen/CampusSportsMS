package org.glxy.sass.service;

import org.glxy.sass.entity.VO.UsersSex;

import java.util.List;

public interface UsersSexService {

    /**
     * 用户信息导出表格
     * @return
     */
    List<UsersSex> listAll();
}
