package org.glxy.sass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.glxy.sass.entity.VO.UsersNum;

@Mapper
public interface UsersNumMapper extends BaseMapper<UsersNum> {
    /**
     * 统计报名人数（男，女，总  现统计用户表）
     * @return
     */
    UsersNum selectSex();
}
