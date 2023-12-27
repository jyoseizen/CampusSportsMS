package org.glxy.sass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.Applicationform;
import org.glxy.sass.mapper.ApplicationformMapper;
import org.glxy.sass.service.ApplicationformService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApplicationformServiceImpl extends ServiceImpl<ApplicationformMapper, Applicationform> implements ApplicationformService {

    @Resource
    ApplicationformMapper applicationformMapper;


    /**
     * 用户报名列表查重
     * @param usersStnumber
     * @param competitionNb
     * @return
     */
    @Override
    public List selectFrom(String usersStnumber, String competitionNb){
       return applicationformMapper.selectFrom(usersStnumber, competitionNb);
    }

    /**
     * 用户报名列表新增
     * @param applicationform
     */
    @Override
    public void addA(Applicationform applicationform){
        applicationformMapper.addA(applicationform);
    }

    /**
     * 删除用户报名记录
     * @param usersStnumber
     * @param competitionNb
     */
    @Override
    public void deleteUser(@Param("usersStnumber") String usersStnumber, @Param("competitionNb") String competitionNb){
        applicationformMapper.deleteUser(usersStnumber, competitionNb);
    }

    /**
     * 根据学号删除报名表
     *
     * @param usersStnumber
     */
    @Override
    public void deleteAppUsersStnumber(String usersStnumber){
        applicationformMapper.deleteAppUsersStnumber(usersStnumber);
    }

    /**
     * 根据比赛编号删除报名表
     *
     * @param competitionNb
     */
    @Override
    public void deleteAppCompetitionNb(String competitionNb){
        applicationformMapper.deleteAppCompetitionNb(competitionNb);
    }
}
