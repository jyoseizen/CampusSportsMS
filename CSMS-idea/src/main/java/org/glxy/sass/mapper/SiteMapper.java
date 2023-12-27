package org.glxy.sass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.glxy.sass.entity.Site;

import java.util.List;

@Mapper
public interface SiteMapper extends BaseMapper<Site> {

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
