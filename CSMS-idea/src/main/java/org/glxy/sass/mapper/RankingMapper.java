package org.glxy.sass.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.glxy.sass.entity.Ranking;

import java.util.List;

@Mapper
public interface RankingMapper {

    /**
     * 统计各学院积分并排序
     * @return
     */
    List<Ranking> selectRanking();


}
