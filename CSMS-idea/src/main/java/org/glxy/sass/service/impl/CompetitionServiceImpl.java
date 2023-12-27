package org.glxy.sass.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.Competition;
import org.glxy.sass.entity.Enroll;
import org.glxy.sass.mapper.CompetitionMapper;
import org.glxy.sass.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition> implements CompetitionService {
    @Resource
    CompetitionMapper competitionMapper;

//    @Override
//    public List<Competition> selectByNb(Integer PageNum, Integer PageSize, String competitionNe, String competitionStart, String competitionEnd) {
//        return competitionMapper.selectByNb(PageNum, PageSize, competitionNe,competitionStart,competitionEnd);
//    }


    /**
     * 根据时间段，名字模糊查询
     *
     * @param competitionNe
     * @param competitionStart
     * @param competitionEnd
     * @return
     */
    @Override
    public Page<Competition> selectByNb(Page<Competition> page,
                                        @Param("competitionNe") String competitionNe,
                                        @Param("competitionStart") String competitionStart,
                                        @Param("competitionEnd") String competitionEnd) {
        return competitionMapper.selectByNb(page, competitionNe, competitionStart, competitionEnd);
    }

    /**
     * 比赛编号删除
     * @param competitionNb
     */
    @Override
    public void delete(@Param("competitionNb") String competitionNb) {
        competitionMapper.delete(competitionNb);
    }


    @Override
    public List<Enroll> selectByEnroll() {
        return competitionMapper.selectByEnroll();
    }

    /**
     * 比赛新增（新）
     * @param competition
     */
    @Override
    public void addCompetition(Competition competition){
        competitionMapper.addCompetition(competition);
    }

    /**
     * 比赛剩余参赛人数-1
     * @param competitionNb
     */
    @Override
    public void updateCompetition(@Param("competitionNb")String competitionNb){
        competitionMapper.updateCompetition(competitionNb);
    }

    /**
     * 查询比赛剩余人数
     * @param competitionNb
     */
    @Override
    public Integer selectCompetitionTnor(@Param("competitionNb")String competitionNb){
        return competitionMapper.selectCompetitionTnor(competitionNb);
    }

    /**
     * 比赛剩余参赛人数+1
     * @param competitionNb
     */
    @Override
    public void updateCompetitionTnor(@Param("competitionNb")String competitionNb){
        competitionMapper.updateCompetitionTnor(competitionNb);
    }

    /**
     * 用户取消报名(查询是否超过报名时间)
     * @param usersStnumber
     * @param competitionNb
     * @return
     */
    @Override
    public List selectApplyEnd(String usersStnumber,String competitionNb){
        return competitionMapper.selectApplyEnd(usersStnumber, competitionNb);
    }

    /**
     * 删除比赛用户报名记录
     * @param usersStnumber
     * @param competitionNb
     */
    @Override
    public void deleteComUser(@Param("usersStnumber") String usersStnumber,@Param("competitionNb") String competitionNb){
        competitionMapper.deleteComUser(usersStnumber, competitionNb);
    }

    /**
     * 查询比赛是否超过报名时间
     * @param competitionNb
     * @return
     */
    @Override
    public List selectEnd(String competitionNb){
        return competitionMapper.selectEnd(competitionNb);
    }


    /**
     * 查询比赛参赛性别是否为男
     * @param competitionNb
     * @return
     */
    @Override
    public List selectCompetitionSex(String competitionNb){
        return competitionMapper.selectCompetitionSex(competitionNb);
    }

    /**
     * 查询比赛参赛性别是否为女
     * @param competitionNb
     * @return
     */
    @Override
    public List selectCompetitionSexGirl(String competitionNb){
        return competitionMapper.selectCompetitionSexGirl(competitionNb);
    }

    /**
     * 以场地查询比赛
     * @param competitionSite
     * @return
     */
    @Override
    public List<Competition> selectCompetitionSite(String competitionSite){
        return competitionMapper.selectCompetitionSite(competitionSite);
    }
}
