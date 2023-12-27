package org.glxy.sass.controller;

import com.alibaba.fastjson.JSON;
import org.glxy.sass.entity.Ranking;
import org.glxy.sass.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    RankingService rankingService;

    @GetMapping("/selectRanking")
    public String selectRanking() {
        List<Ranking> rankings = rankingService.selectRanking();
        HashMap<String, Object> res = new HashMap<>();
        List<String> collegeName = new ArrayList<>();
        List<Integer> rankingIntegral = new ArrayList<>();

        for (int i = 0; i < rankings.size(); i++ ){
            collegeName.add(rankings.get(i).getCollegeName());
            rankingIntegral.add(rankings.get(i).getRankingIntegral());
        }
        res.put("collegeName",collegeName);
        res.put("rankingIntegral",rankingIntegral);
        String res_string = JSON.toJSONString(res);

        System.out.println("排行图标数据 ==》"+res_string);

        return res_string;
    }
}
