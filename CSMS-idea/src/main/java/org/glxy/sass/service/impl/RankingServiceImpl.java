package org.glxy.sass.service.impl;

import org.glxy.sass.entity.Ranking;
import org.glxy.sass.mapper.RankingMapper;
import org.glxy.sass.service.RankingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RankingServiceImpl implements RankingService {

    @Resource
    RankingMapper rankingMapper;

    /**
     * 统计各学院积分并排序
     * @return
     */
    @Override
    public List<Ranking> selectRanking(){
        return rankingMapper.selectRanking();
    }
}
