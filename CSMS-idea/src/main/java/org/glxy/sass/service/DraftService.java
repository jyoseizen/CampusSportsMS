package org.glxy.sass.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.Draft;

public interface DraftService extends IService<Draft> {

    /**
     * 加油稿状态修改
     * @param draft
     */
    void updateState(Draft draft);

    Page<Draft> selectBySm(Page<Draft> page,
                           @Param("usersStnumber") String usersStnumber,
                           @Param("usersName")String usersName,
                           @Param("collegeName")String collegeName);

    /**
     * 用户查询审核通过加油稿（一对多）
     * @param page
     * @return
     */
    Page<Draft> selectImgUrl(Page<Draft> page);

    /**
     * 根据学号删除加油稿
     * @param usersStnumber
     */
    void deleteUsersStnumber(String usersStnumber);
}
