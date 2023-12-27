package org.glxy.sass.controller;

import com.alibaba.fastjson.JSON;
import org.glxy.sass.entity.Echarts;
import org.glxy.sass.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    EchartsService echartsService;

    /**
     * Echarts图表数据（各学院报名人数统计（现统计各学院用户））
     * @return
     */
    @GetMapping("/getList")
    public String getListEcharts(HttpServletRequest request){
//        List<Echarts> echarts = new List<Echarts>;
//        echarts = echartsService.getListEcharts();
//        System.out.println("echarts"+echarts);
//        return echarts;
        List<Echarts> echarts = echartsService.getListEcharts();

        HashMap<String, Object> res = new HashMap<>();
        List<String> name = new ArrayList<>();
        List<Integer> num = new ArrayList<>();

        for( int i = 0; i < echarts.size(); i++ ){
            name.add(echarts.get(i).getCollegeName());
            num.add(echarts.get(i).getNum());
        }
        res.put("name",name);
        res.put("num",num);
        System.out.println(res);
        String res_string = JSON.toJSONString(res);  //将集合转换成字符串
        System.out.println("res:"+res_string);
//        return res_string;

        System.out.println("char:"+echarts);
        System.out.println("token ======>"+request.getHeader("token"));
        return res_string;
    }

    @GetMapping("/getList2")
    public List<Echarts> getEcharts(){
        List<Echarts> echarts = echartsService.getListEcharts();
        return echarts;
    }
}
