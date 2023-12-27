package org.glxy.sass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.College;

import java.util.List;

@Mapper
public interface CollegeMapper extends BaseMapper<College> {

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
