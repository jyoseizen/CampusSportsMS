package org.glxy.sass.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.glxy.sass.entity.Echarts;

import java.util.List;

@Mapper
public interface EchartsMapper {

     /**
      * Echarts图表数据（各学院报名人数统计）
      * @return
      */
     List<Echarts> getListEcharts();


}
