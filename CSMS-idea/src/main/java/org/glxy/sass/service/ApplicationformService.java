package org.glxy.sass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.Applicationform;

import java.util.List;

public interface ApplicationformService extends IService<Applicationform> {

    /**
     * 用户报名列表查重
     * @param usersStnumber
     * @param competitionNb
     * @return
     */
    List selectFrom(String usersStnumber, String competitionNb);

    /**
     * 用户报名列表新增
     * @param applicationform
     */
    void addA(Applicationform applicationform);

    /**
     * 删除用户报名记录
     * @param usersStnumber
     * @param competitionNb
     */
    void deleteUser(@Param("usersStnumber") String usersStnumber, @Param("competitionNb") String competitionNb);

    /**
     * 根据学号删除报名表
     *
     * @param usersStnumber
     */
    void deleteAppUsersStnumber(String usersStnumber);

    /**
     * 根据比赛编号删除报名表
     *
     * @param competitionNb
     */
    void deleteAppCompetitionNb(String competitionNb);
}
