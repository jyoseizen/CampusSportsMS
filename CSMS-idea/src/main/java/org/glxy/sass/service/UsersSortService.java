package org.glxy.sass.service;

import org.glxy.sass.entity.VO.UsersSort;

import java.util.List;

public interface UsersSortService {

    /**
     * 以性别统计男女报名数量
     * @return
     */
    List<UsersSort> selectSum();
}
