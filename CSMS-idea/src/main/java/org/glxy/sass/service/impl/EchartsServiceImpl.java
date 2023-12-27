package org.glxy.sass.service.impl;

import org.glxy.sass.entity.Echarts;
import org.glxy.sass.mapper.EchartsMapper;
import org.glxy.sass.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchartsServiceImpl implements EchartsService {

    @Autowired
    EchartsMapper echartsMapper;

    /**
     * Echarts图表数据（各学院报名人数统计）
     * @return
     */
    @Override
    public List<Echarts> getListEcharts(){
        return echartsMapper.getListEcharts();
    }
}
