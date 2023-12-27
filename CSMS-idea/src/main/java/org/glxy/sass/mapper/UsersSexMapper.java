package org.glxy.sass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.glxy.sass.entity.VO.UsersSex;

import java.util.List;

@Mapper
public interface UsersSexMapper extends BaseMapper<UsersSex> {

    /**
     * 用户信息导出表格
     * @return
     */
    List<UsersSex> listAll();
}
