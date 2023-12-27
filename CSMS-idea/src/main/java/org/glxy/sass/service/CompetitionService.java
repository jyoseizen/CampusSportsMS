package org.glxy.sass.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.Competition;
import org.glxy.sass.entity.Enroll;
import org.glxy.sass.entity.Users;

import java.time.LocalDateTime;
import java.util.List;

public interface CompetitionService extends IService<Competition> {

//    List<Competition> selectByNb(Integer PageNum, Integer PageSize, String competitionNe, String competitionStart, String competitionEnd);

    /**
     * 根据时间段，名字模糊查询
     * @param competitionNe
     * @param competitionStart
     * @param competitionEnd
     * @return
     */
    Page<Competition> selectByNb(Page<Competition> page,
                                 @Param("competitionNe") String competitionNe,
                                 @Param("competitionStart")String competitionStart,
                                 @Param("competitionEnd")String competitionEnd);

    /**
     * 比赛编号删除
     * @param competitionNb
     */
    void delete(@Param("competitionNb") String competitionNb);


    List<Enroll> selectByEnroll();

    /**
     * 比赛新增（新）
     * @param competition
     */
    void addCompetition(Competition competition);

    /**
     * 比赛剩余参赛人数-1
     * @param competitionNb
     */
    void updateCompetition(@Param("competitionNb")String competitionNb);

    /**
     * 查询比赛剩余人数
     * @param competitionNb
     */
    Integer selectCompetitionTnor(@Param("competitionNb")String competitionNb);

    /**
     * 比赛剩余参赛人数+1
     * @param competitionNb
     */
    void updateCompetitionTnor(@Param("competitionNb")String competitionNb);

    /**
     * 用户取消报名(查询是否超过报名时间)
     * @param usersStnumber
     * @param competitionNb
     * @return
     */
    List selectApplyEnd(String usersStnumber,String competitionNb);

    /**
     * 删除比赛用户报名记录
     * @param usersStnumber
     * @param competitionNb
     */
    void deleteComUser(@Param("usersStnumber") String usersStnumber,@Param("competitionNb") String competitionNb);

    /**
     * 查询比赛是否超过报名时间
     * @param competitionNb
     * @return
     */
    List selectEnd(String competitionNb);

    /**
     * 查询比赛参赛性别是否为男
     * @param competitionNb
     * @return
     */
    List selectCompetitionSex(String competitionNb);

    /**
     * 查询比赛参赛性别是否为女
     * @param competitionNb
     * @return
     */
    List selectCompetitionSexGirl(String competitionNb);

    /**
     * 以场地查询比赛
     * @param competitionSite
     * @return
     */
    List<Competition> selectCompetitionSite(String competitionSite);

}
