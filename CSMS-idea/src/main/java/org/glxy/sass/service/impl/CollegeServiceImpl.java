package org.glxy.sass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.College;
import org.glxy.sass.mapper.CollegeMapper;
import org.glxy.sass.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService {
    @Resource
    CollegeMapper collegeMapper;

    public void deleteCollege(@Param("collegeName") String collegeName){
        collegeMapper.deleteCollege(collegeName);
    }

    /**
     * 查询学院名字（动态字典）
     * @return
     */
    @Override
    public List<College> selectCollegeName(){
        return collegeMapper.selectCollegeName();
    }

    /**
     * 以学院名查询用户
     * @param collegeName
     * @return
     */
    @Override
    public List selectUsersCollegeName(String collegeName){
        return collegeMapper.selectUsersCollegeName(collegeName);
    }
}
