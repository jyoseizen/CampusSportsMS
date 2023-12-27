package org.glxy.sass.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.glxy.sass.config.Result;
import org.glxy.sass.entity.Enroll;
import org.glxy.sass.mapper.EnrollMapper;
import org.glxy.sass.service.CompetitionService;
import org.glxy.sass.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Api
@RestController
@RequestMapping("/enroll")
public class EnrollController {

    @Autowired
    EnrollService enrollService;

    @Autowired
    CompetitionService competitionService;

    @Resource
    EnrollMapper enrollMapper;

    /**
     * 根据比赛名，学院名，用户名查询报名列表
     *
     * @param PageNum
     * @param PageSize
     * @param competitionNe
     * @param collegeName
     * @param usersName
     * @return
     */
    @GetMapping("/selectEnAll")
    public Result<?> selectAll(@RequestParam(defaultValue = "1") Integer PageNum,
                               @RequestParam(defaultValue = "20") Integer PageSize,
                               String competitionNe, String collegeName, String usersName) {
        Page<Enroll> enrollPage = enrollService.selectAll(new Page<>(PageNum, PageSize), competitionNe, collegeName, usersName);
        return Result.success(enrollPage);
    }

    /**
     * 用户根据自己的学号获取报名列表
     *
     * @param PageNum
     * @param PageSize
     * @param usersStnumber
     * @return
     */
    @GetMapping("/selectBySber")
    public Result<?> selectBySber(@RequestParam(defaultValue = "1") Integer PageNum,
                                  @RequestParam(defaultValue = "20") Integer PageSize,
                                  String usersStnumber) {
        Page<Enroll> enrollSber = enrollService.selectBySber(new Page<>(PageNum, PageSize), usersStnumber);
        return Result.success(enrollSber);
    }

    /**
     * （比赛报名表
     * ）用户报名列表存库
     *
     * @return
     */
    @GetMapping("/getlist")
    public Result<?> getlist() {
        List<Enroll> list = competitionService.selectByEnroll();  //查询用户报名列表获取信息
        for (int i = 0; i < list.size(); i++) {
            String competitionNbs = list.get(i).getCompetitionNb();
            String usersStnumbers = list.get(i).getUsersStnumber();
            String map = String.valueOf(enrollService.selectRepeat(competitionNbs, usersStnumbers)); //通过比赛编号和学号查询比赛报名表是否重复
            if (map == "[]") {
                Enroll ienroll = new Enroll();
                ienroll.setCompetitionNb(list.get(i).getCompetitionNb());
                ienroll.setCompetitionNe(list.get(i).getCompetitionNe());
                ienroll.setCompetitionStart(list.get(i).getCompetitionStart());
                ienroll.setCompetitionEnd(list.get(i).getCompetitionEnd());
                ienroll.setCompetitionSite(list.get(i).getCompetitionSite());
                ienroll.setUsersStnumber(list.get(i).getUsersStnumber());
                ienroll.setUsersName(list.get(i).getUsersName());
                ienroll.setUsersSex(list.get(i).getUsersSex());
                ienroll.setUsersPhone(list.get(i).getUsersPhone());
                ienroll.setCollegeName(list.get(i).getCollegeName());
                enrollService.add(ienroll);
//                System.out.println("==>" + ienroll);
//                System.out.println("正常数据");
            } else {
//                System.out.println("重复数据");
                continue;
            }
        }
        return Result.success();
    }

    /**
     * 写入用户报名列表
     *
     * @param enroll
     */
    @PostMapping("/add")
    public Result<?> add(Enroll enroll) {
        enrollService.add(enroll);
        return Result.success();
    }

    /**
     * 查询表中数据是否重复
     *
     * @param competitionNb
     * @param usersStnumber
     * @return
     */
    @PostMapping("/Repeat")
    public List selectRepeat(String competitionNb, String usersStnumber) {
        return enrollService.selectRepeat(competitionNb, usersStnumber);
    }

    /**
     * 比赛打分
     *
     * @param enroll
     * @return
     */
    @PutMapping("/updateCompetition")
    public Result<?> update(Enroll enroll) {
        enrollMapper.updateById(enroll);
        return Result.success();
    }


    /**
     * 获取用户报名比赛个数
     *
     * @param usersStnumber
     * @return
     */
    @GetMapping("/selectSber")
    public List selectSber(String usersStnumber) {
        System.out.println(enrollService.selectSber(usersStnumber).size());
        return enrollService.selectSber(usersStnumber);
    }


//    @GetMapping("time")
//    public  void times() {
//        LocalDate localDate = LocalDate.now();
//        LocalTime localTime = LocalTime.now();
//        DateTimeFormatter f4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String time = localDate.atTime(localTime).format(f4);
//        System.out.println(time);
//    }


}
