package org.glxy.sass.service;

import org.glxy.sass.entity.Echarts;

import java.util.List;

public interface EchartsService {

     /**
      * Echarts图表数据（各学院报名人数统计）
      * @return
      */
     List<Echarts> getListEcharts();
}
