package org.glxy.sass.controller;

import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.config.Result;
import org.glxy.sass.entity.Applicationform;
import org.glxy.sass.service.ApplicationformService;
import org.glxy.sass.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/applicationfrom")
public class ApplicationformController {

    @Autowired
    ApplicationformService applicationformService;

    @Autowired
    CompetitionService competitionService;

    /**
     * 用户报名列表查重
     *
     * @param usersStnumber
     * @param competitionNb
     * @return
     */
    @PostMapping("/selectFrom")
    public List selectFrom(String usersStnumber, String competitionNb) {
        return applicationformService.selectFrom(usersStnumber, competitionNb);
    }


    /**
     * 用户报名表新增
     *
     * @param applicationform
     * @return
     */
    @PostMapping("/insertaddA")
    public Result<?> addA(@RequestBody Applicationform applicationform) {
        applicationformService.addA(applicationform);
        return Result.success();
    }


    /**
     * 用户取消报名
     *
     * @param usersStnumber
     * @param competitionNb
     * @return
     */
    @GetMapping("/cancel")
    public Result<?> cancel(@RequestParam String usersStnumber, @RequestParam String competitionNb) {
        //查询比赛是否结束报名
        String map = String.valueOf(competitionService.selectApplyEnd(usersStnumber, competitionNb));
        if (map == "[]") {   //为空则表示还未结束报名，用户可以删除报名记录
            applicationformService.deleteUser(usersStnumber, competitionNb); //删除中间表
            competitionService.deleteComUser(usersStnumber, competitionNb);  //删除比赛用户报名名单
            competitionService.updateCompetitionTnor(competitionNb);  //剩余参赛人数+1
            return Result.success();
        } else {
            return Result.error("600");
        }

    }

    /**
     * 删除用户报名记录
     *
     * @param usersStnumber
     * @param competitionNb
     */
    @DeleteMapping("/deleteUsers")
    public Result<?> deleteUser(String usersStnumber, String competitionNb) {
        applicationformService.deleteUser(usersStnumber, competitionNb);
        return Result.success();
    }
}
