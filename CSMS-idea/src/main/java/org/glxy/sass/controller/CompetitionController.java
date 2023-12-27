package org.glxy.sass.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.config.Result;
import org.glxy.sass.entity.Applicationform;
import org.glxy.sass.entity.Competition;
import org.glxy.sass.entity.Enroll;
import org.glxy.sass.entity.Users;
import org.glxy.sass.mapper.CompetitionMapper;
import org.glxy.sass.service.ApplicationformService;
import org.glxy.sass.service.CompetitionService;
import org.glxy.sass.service.EnrollService;
import org.glxy.sass.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletionService;

/**
 * 比赛信息
 */
@Api
@RestController
@RequestMapping("/competition")
public class CompetitionController {
    @Autowired
    CompetitionService competitionService;

    @Autowired
    EnrollService enrollService;

    @Autowired
    ApplicationformService applicationformService;

    @Autowired
    UsersService usersService;

    @Resource
    CompetitionMapper competitionMapper;

    /**
     * 比赛信息分页查询
     *
     * @param PageNum
     * @param PageSize
     * @param search
     * @return
     */
    @GetMapping("/search")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer PageNum,
                              @RequestParam(defaultValue = "100") Integer PageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Competition> wrapper = Wrappers.<Competition>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Competition::getCompetitionNb, search);
        }
        Page<Competition> competitionPage = competitionMapper.selectPage(new Page<>(PageNum, PageSize), wrapper);
        return Result.success(competitionPage);
    }

    /**
     * 根据时间段，名字模糊查询
     *
     * @param PageNum
     * @param PageSize
     * @param competitionNe
     * @param competitionStart
     * @param competitionEnd
     * @return
     */
    @GetMapping("/selectByNb")
    public Result<?> selectByNb(@RequestParam(defaultValue = "1") Integer PageNum,
                                @RequestParam(defaultValue = "20") Integer PageSize,
                                String competitionNe, String competitionStart, String competitionEnd) {
//        Page<Competition> competitionPage = competitionMapper.selectByNb(new Page<>(PageNum, PageSize), competitionNe,competitionStart,competitionEnd);
        Page<Competition> competitionPage = competitionService.selectByNb(new Page<>(PageNum, PageSize), competitionNe, competitionStart, competitionEnd);
        return Result.success(competitionPage);
//        return Result.success(competitionService.selectByNb(PageNum, PageSize, competitionNe,competitionStart,competitionEnd));

    }

    /**
     * 比赛增加
     *
     * @param competition
     * @return
     */
    @PostMapping("/insertCompetition")
    public Result<?> insert(@RequestBody Competition competition) {
        Competition competitionN = competitionMapper.selectOne(Wrappers.<Competition>lambdaQuery().eq(Competition::getCompetitionNb, competition.getCompetitionNb()));
        if (competitionN != null) {
            return Result.error("-1", "比赛编号已存在");
        } else {
            Competition icompetition = new Competition();
            icompetition.setCompetitionNb(competition.getCompetitionNb());
            icompetition.setCompetitionNe(competition.getCompetitionNe());
            icompetition.setCompetitionSite(competition.getCompetitionSite());
            icompetition.setCompetitionPe(competition.getCompetitionPe());
            icompetition.setCompetitionTnor(competition.getCompetitionPe());
            icompetition.setCompetitionStart(competition.getCompetitionStart());
            icompetition.setCompetitionEnd(competition.getCompetitionEnd());
            icompetition.setApplyStart(competition.getApplyStart());
            icompetition.setApplyEnd(competition.getApplyEnd());
            competitionService.addCompetition(icompetition);
//            competitionMapper.insert(competition);
            System.out.println("222");
            return Result.success();
        }
    }

    /**
     * 比赛编号修改
     *
     * @param cn
     * @return
     */
    @PutMapping("/updateScore")
    public Result<?> update(Competition cn) {
        competitionMapper.updateById(cn);
        return Result.success();
    }

    /**
     * 比赛编号删除
     *
     * @param competitionNb
     * @return
     */
    @DeleteMapping("/deleteCompetition")
    public Result<?> delete(String competitionNb) {
        competitionService.delete(competitionNb);  //删除比赛
        applicationformService.deleteAppCompetitionNb(competitionNb); //删除中间表
        enrollService.deleteEnCompetitionNb(competitionNb);  //删除报名名单
        return Result.success();
    }

    @GetMapping("/selectByEnroll")
    public List<Enroll> selectByEnroll() {
        List<Enroll> list = competitionService.selectByEnroll();
        System.out.println(list);
        return competitionService.selectByEnroll();
    }

    /**
     * 比赛新增（新）
     */
    @PostMapping("/addCompetition")
    public Result<?> addCompetition(Competition competition) {
        competitionService.addCompetition(competition);
        return Result.success("成功");
    }

    /**
     * 用户报名比赛
     *
     * @param usersStnumber
     * @param competitionNb
     */
    @GetMapping("/updateCompetition")
    public Result<?> insertCompetition(@RequestParam String usersStnumber, @RequestParam String competitionNb) {
        String map = String.valueOf(applicationformService.selectFrom(usersStnumber, competitionNb)); //查询用户报名列表是否有重复，有则不为空
        String sexMap = String.valueOf(competitionService.selectCompetitionSex(competitionNb));  //判断比赛是否为男
        String girlMap = String.valueOf(competitionService.selectCompetitionSexGirl(competitionNb)); //判断比赛是否为女
        int sun = (enrollService.selectSber(usersStnumber).size());
        Integer isex = usersService.selectByUsersSex(usersStnumber);   //判断用户性别
        if (sexMap != "[]" && girlMap == "[]") {                        //证明参赛性别为男
            if (isex != 0) {            //证明用户性别不为男
                return Result.error("报名失败");
            }
        } else if (sexMap == "[]" && girlMap != "[]") {                  //证明参赛性别为女
            if (isex != 1) {        //证明用户性别不为女
                return Result.error("报名失败");
            }
        }
        if (sun >= 3) {         //判断用户报名个数
            return Result.error("报名失败");
        }
        if (map == "[]") {  //为空则可以报名
            Integer num = competitionService.selectCompetitionTnor(competitionNb);
            String nun = String.valueOf(competitionService.selectEnd(competitionNb));
            if (num == 0) {
                return Result.error("报名失败");
            } else if (nun != "[]") {
                return Result.error("报名失败");
            } else {
                Applicationform iapplicationform = new Applicationform();
                iapplicationform.setUsersStnumber(usersStnumber);
                iapplicationform.setCompetitionNb(competitionNb);
                applicationformService.addA(iapplicationform);  //报名信息写进用户报名表
//                插入报名表
                
//                List<Enroll> list = competitionService.selectByEnroll();  //查询用户报名列表获取信息
//                for (int i = 0; i < list.size(); i++) {
//                    String competitionNbs = list.get(i).getCompetitionNb();
//                    String usersStnumbers = list.get(i).getUsersStnumber();
//                    String maps = String.valueOf(enrollService.selectRepeat(competitionNbs, usersStnumbers)); //通过比赛编号和学号查询比赛报名表是否重复
//                    if (maps == "[]") {
//                        Enroll ienroll = new Enroll();
//                        ienroll.setCompetitionNb(list.get(i).getCompetitionNb());
//                        ienroll.setCompetitionNe(list.get(i).getCompetitionNe());
//                        ienroll.setCompetitionStart(list.get(i).getCompetitionStart());
//                        ienroll.setCompetitionEnd(list.get(i).getCompetitionEnd());
//                        ienroll.setCompetitionSite(list.get(i).getCompetitionSite());
//                        ienroll.setUsersStnumber(list.get(i).getUsersStnumber());
//                        ienroll.setUsersName(list.get(i).getUsersName());
//                        ienroll.setUsersSex(list.get(i).getUsersSex());
//                        ienroll.setUsersPhone(list.get(i).getUsersPhone());
//                        ienroll.setCollegeName(list.get(i).getCollegeName());
//                        enrollService.add(ienroll);
////                        System.out.println("==>" + ienroll);
////                        System.out.println("正常数据");
//                    } else {
////                        System.out.println("重复数据");
//                        continue;
//                    }
//                }
                competitionService.updateCompetition(competitionNb);  //剩余参赛人数减1
                return Result.success();
            }
        } else {
            return Result.error("报名失败");
        }
    }

    /**
     * 比赛剩余参赛人数-1
     *
     * @param competitionNb
     */
    @GetMapping("/updateCompetitionTnor")
    public Result<?> updateCompetition(String competitionNb) {
        competitionService.updateCompetition(competitionNb);
        return Result.success();
    }

    /**
     * 查询比赛剩余人数
     *
     * @param competitionNb
     */
    @GetMapping("/selectCompetitionTnor")
    public Integer selectCompetitionTnor(String competitionNb) {
//        String i = competitionService.selectCompetitionTnor(competitionNb);
        return competitionService.selectCompetitionTnor(competitionNb);
    }

    /**
     * 比赛剩余参赛人数-1
     *
     * @param competitionNb
     */
    @GetMapping("/addCompetitionTnor")
    public Result<?> updateCompetitionTnor(String competitionNb) {
        competitionService.updateCompetitionTnor(competitionNb);
        return Result.success();
    }

    /**
     * 用户取消报名(查询是否超过报名时间)
     *
     * @param usersStnumber
     * @param competitionNb
     * @return
     */
    @PostMapping("/selectApplyEnd")
    public List selectApplyEnd(String usersStnumber, String competitionNb) {
        return competitionService.selectApplyEnd(usersStnumber, competitionNb);
    }

    /**
     * 删除比赛用户报名记录
     *
     * @param usersStnumber
     * @param competitionNb
     */
    @PostMapping("/deleteComUser")
    public Result<?> deleteComUser(String usersStnumber, String competitionNb) {
        competitionService.deleteComUser(usersStnumber, competitionNb);
        return Result.success();
    }

    /**
     * 查询比赛是否超过报名时间
     *
     * @param competitionNb
     * @return
     */
    @GetMapping("/selectEnd")
    public List selectEnd(String competitionNb) {
        return competitionService.selectEnd(competitionNb);
    }

    /**
     * 查询比赛参赛性别是否为男
     *
     * @param competitionNb
     * @return
     */
    @GetMapping("/selectCompetitionSex")
    public List selectCompetitionSex(String competitionNb) {
        return competitionService.selectCompetitionSex(competitionNb);
    }

    /**
     * 查询比赛参赛性别是否为女
     *
     * @param competitionNb
     * @return
     */
    @GetMapping("/selectCompetitionSexGirl")
    public List selectCompetitionSexGirl(String competitionNb) {
        return competitionService.selectCompetitionSexGirl(competitionNb);
    }

    /**
     * 以场地查询比赛
     *
     * @param competitionSite
     * @return
     */
    @GetMapping("/selectCompetitionSite")
    public List<Competition> selectCompetitionSite(String competitionSite) {
        return competitionMapper.selectCompetitionSite(competitionSite);
    }

}
