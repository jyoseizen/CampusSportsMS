package org.glxy.sass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.College;
import org.glxy.sass.entity.Competition;

import java.util.List;

public interface CollegeService extends IService<College> {

    void deleteCollege(@Param("collegeName") String collegeName);

    /**
     * 查询学院名字（动态字典）
     * @return
     */
    List<College> selectCollegeName();

    /**
     * 以学院名查询用户
     * @param collegeName
     * @return
     */
    List selectUsersCollegeName(String collegeName);
}
