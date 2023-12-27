package org.glxy.sass.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.Enroll;
import org.glxy.sass.mapper.EnrollMapper;
import org.glxy.sass.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EnrollServiceImpl extends ServiceImpl<EnrollMapper, Enroll> implements EnrollService {

    @Resource
    EnrollMapper enrollMapper;

    /**
     * 根据比赛名，学院名，用户名查询报名列表
     * @param page
     * @param competitionNe
     * @param collegeName
     * @param usersName
     * @return
     */
    @Override
    public Page<Enroll> selectAll(Page<Enroll> page,
                                  @Param("competitionNe") String competitionNe,
                                  @Param("collegeName") String collegeName,
                                  @Param("usersName") String usersName) {
        return enrollMapper.selectAll(page, competitionNe, collegeName, usersName);
    }

    /**
     * 用户根据自己的学号获取报名列表
     * @param page
     * @param usersStnumber
     * @return
     */
    @Override
    public Page<Enroll> selectBySber(Page<Enroll> page,@Param("usersStnumber") String usersStnumber){
        return enrollMapper.selectBySber(page, usersStnumber);
    }

    /**
     * 写入用户报名列表
     *
     * @param enroll
     */
    @Override
    public void add(Enroll enroll) {
        enrollMapper.add(enroll);
    }

    /**
     * 查询表中数据是否重复
     *
     * @param competitionNb
     * @param usersStnumber
     * @return
     */
    @Override
    public List selectRepeat(String competitionNb, String usersStnumber) {
        return enrollMapper.selectRepeat(competitionNb, usersStnumber);
    }

    /**
     * 获取用户报名比赛个数
     * @param usersStnumber
     * @return
     */
    @Override
    public List selectSber(String usersStnumber){
        return enrollMapper.selectSber(usersStnumber);
    }

    /**
     * 根据学号删除
     * @param usersStnumber
     */
    @Override
    public void deleteEnUsersStnumber(String usersStnumber){
        enrollMapper.deleteEnUsersStnumber(usersStnumber);
    }

    /**
     * 根据比赛编号删除
     *
     * @param competitionNb
     */
    @Override
    public void deleteEnCompetitionNb(String competitionNb){
        enrollMapper.deleteEnCompetitionNb(competitionNb);
    }
}
