package org.glxy.sass.service.impl;

import org.glxy.sass.entity.VO.UsersSort;
import org.glxy.sass.mapper.UsersSortMapper;
import org.glxy.sass.service.UsersSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersSortServiceImpl implements UsersSortService {

    @Autowired
    UsersSortMapper usersSortMapper;

    /**
     * 以性别统计男女报名数量
     * @return
     */
    @Override
    public List<UsersSort> selectSum(){
        List<UsersSort> sexNum = usersSortMapper.selectSum();
        List<UsersSort> sexNum2 = new ArrayList<>();
        System.out.println("sexNum:"+sexNum);
        for (int i=0; i < sexNum.size(); i++ ){
            UsersSort sexName = new UsersSort();

            if(sexNum.get(i).getUsersSex().equals("1")){
                sexName.setUsersSex("女");
                sexName.setSum(sexNum.get(i).getSum());
                sexNum2.add(sexName);

            }else {
                sexName.setUsersSex("男");
                sexName.setSum(sexNum.get(i).getSum());
                sexNum2.add(sexName);
            }
        }
        return sexNum2;
    }
}
