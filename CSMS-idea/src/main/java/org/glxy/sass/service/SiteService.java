package org.glxy.sass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.glxy.sass.entity.Site;

import java.util.List;

public interface SiteService extends IService<Site> {

    /**
     * 查询比赛地点（字典）
     * @return
     */
    List<Site> selectCompetitionSite();

    /**
     * 查询学院名称
     * @param siteId
     */
    String selectByCompetitionSite(Integer siteId);
}
