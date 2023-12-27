package org.glxy.sass.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.glxy.sass.config.Result;
import org.glxy.sass.entity.College;
import org.glxy.sass.entity.Competition;
import org.glxy.sass.entity.Site;
import org.glxy.sass.mapper.SiteMapper;
import org.glxy.sass.service.ApplicationformService;
import org.glxy.sass.service.CompetitionService;
import org.glxy.sass.service.EnrollService;
import org.glxy.sass.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api
@RestController
@RequestMapping("/site")
public class SiteController {

    @Autowired
    SiteService siteService;

    @Autowired
    CompetitionService competitionService;

    @Autowired
    ApplicationformService applicationformService;

    @Autowired
    EnrollService enrollService;

    @Resource
    SiteMapper siteMapper;


    /**
     * 字典
     *
     * @return
     */
    @GetMapping("/selectSite")
    public List<Site> selectCompetitionSite() {
        return siteService.selectCompetitionSite();
    }

    /**
     * 查询场地全部
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
        LambdaQueryWrapper<Site> wrapper = Wrappers.<Site>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Site::getSiteId, search);
        }
        Page<Site> sitePage = siteMapper.selectPage(new Page<>(PageNum, PageSize), wrapper);
        return Result.success(sitePage);
    }

    /**
     * 场地增加
     *
     * @param site
     * @return
     */
    @PostMapping("/insertSite")
    public Result<?> insert(Site site) {  //加@RequestBody会不兼容
        Site siteNumber = siteMapper.selectOne(Wrappers.<Site>lambdaQuery().eq(Site::getSiteNumber, site.getSiteNumber()));
        Site competitionSite = siteMapper.selectOne(Wrappers.<Site>lambdaQuery().eq(Site::getCompetitionSite, site.getCompetitionSite()));
        if (siteNumber != null) {
            return Result.error("-1", "学院编号已存在");
        }
        if (competitionSite != null) {
            return Result.error("-2", "学院名称已存在");
        }
        siteMapper.insert(site);
        System.out.println("222");
        return Result.success();
    }

    /**
     * 场地修改
     *
     * @param site
     * @return
     */
    @PutMapping("/updateSite")
    public Result<?> update(Site site) {
        siteMapper.updateById(site);
        return Result.success();
    }

    /**
     * 学院删除
     *
     * @param siteId
     * @return
     */
    @DeleteMapping("/deleteSite")
    public Result<?> delete(Integer siteId) {
        String siteName = siteService.selectByCompetitionSite(siteId);
        List<Competition> icompetition = competitionService.selectCompetitionSite(siteName);
        for (int i = 0; i < icompetition.size(); i++) {
            System.out.println(i + "  " + icompetition.get(i).getCompetitionNb());
            competitionService.delete(icompetition.get(i).getCompetitionNb());  //删除比赛
            applicationformService.deleteAppCompetitionNb(icompetition.get(i).getCompetitionNb()); //删除中间表
            enrollService.deleteEnCompetitionNb(icompetition.get(i).getCompetitionNb());  //删除报名名单
        }
        siteMapper.deleteById(siteId);  //删除场地
        return Result.success();
    }


    @GetMapping("/selectByCompetitionSite")
    public String selectByCompetitionSite(Integer siteId) {
        return siteService.selectByCompetitionSite(siteId);
    }
}
