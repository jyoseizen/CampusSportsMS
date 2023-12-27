package org.glxy.sass.service;

import org.glxy.sass.entity.Ranking;

import java.util.List;

public interface RankingService {

    /**
     * 统计各学院积分并排序
     * @return
     */
    List<Ranking> selectRanking();


}
