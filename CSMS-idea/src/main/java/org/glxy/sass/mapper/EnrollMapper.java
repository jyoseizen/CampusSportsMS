package org.glxy.sass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.Enroll;

import java.util.List;

@Mapper
public interface EnrollMapper extends BaseMapper<Enroll> {

    /**
     * 根据比赛名，学院名，用户名查询报名列表
     *
     * @param page
     * @param competitionNe
     * @param collegeName
     * @param usersName
     * @return
     */
    Page<Enroll> selectAll(Page<Enroll> page,
                           @Param("competitionNe") String competitionNe,
                           @Param("collegeName") String collegeName,
                           @Param("usersName") String usersName);

    /**
     * 用户根据自己的学号获取报名列表
     *
     * @param page
     * @param usersStnumber
     * @return
     */
    Page<Enroll> selectBySber(Page<Enroll> page, @Param("usersStnumber") String usersStnumber);


    /**
     * 查询表中数据是否重复
     *
     * @param competitionNb
     * @param usersStnumber
     * @return
     */
    List selectRepeat(String competitionNb, String usersStnumber);

    /**
     * 写入用户报名列表
     *
     * @param enroll
     */
    void add(Enroll enroll);

    /**
     * 获取用户报名比赛个数
     *
     * @param usersStnumber
     * @return
     */
    List selectSber(String usersStnumber);

    /**
     * 根据学号删除
     *
     * @param usersStnumber
     */
    void deleteEnUsersStnumber(String usersStnumber);

    /**
     * 根据比赛编号删除
     *
     * @param competitionNb
     */
    void deleteEnCompetitionNb(String competitionNb);

}
