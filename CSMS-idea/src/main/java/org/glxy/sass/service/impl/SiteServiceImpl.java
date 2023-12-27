package org.glxy.sass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.glxy.sass.entity.Site;
import org.glxy.sass.mapper.SiteMapper;
import org.glxy.sass.service.SiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements SiteService {

    @Resource
    SiteMapper siteMapper;

    /**
     * 查询比赛地点（字典）
     * @return
     */
    @Override
    public List<Site> selectCompetitionSite(){
        return siteMapper.selectCompetitionSite();
    }

    /**
     * 查询学院名称
     * @param siteId
     */
    @Override
    public String selectByCompetitionSite(Integer siteId){
        return siteMapper.selectByCompetitionSite(siteId);
    }
}
