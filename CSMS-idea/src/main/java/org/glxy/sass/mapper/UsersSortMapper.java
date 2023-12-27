package org.glxy.sass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.glxy.sass.entity.VO.UsersSort;

import java.util.List;

@Mapper
public interface UsersSortMapper extends BaseMapper<UsersSort> {

    /**
     * 以性别统计男女报名数量
     * @return
     */
    List<UsersSort> selectSum();
}
