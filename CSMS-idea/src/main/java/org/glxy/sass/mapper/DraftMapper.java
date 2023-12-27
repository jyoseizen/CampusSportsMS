package org.glxy.sass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.Draft;


@Mapper
public interface DraftMapper extends BaseMapper<Draft> {

    /**
     * 根据id修改状态
     * @param draft
     */
    void updateState(Draft draft);

//    /**
//     * 根据学号删除
//     * @param usersStnumber
//     */
//    void delete(@Param("usersStnumber") String usersStnumber);

    /**
     * 根据条件模糊查询
     * @param page
     * @param usersStnumber
     * @param usersName
     * @param collegeName
     * @return
     */
    Page<Draft> selectBySm(Page<Draft> page,
                       @Param("usersStnumber") String usersStnumber,
                       @Param("usersName")String usersName,
                       @Param("collegeName")String collegeName);


    /**
     * 用户查询审核通过加油稿（一对多）
     * @param page
     * @return
     */
    Page<Draft> selectImgUrl(Page<Draft> page);

    /**
     * 根据学号删除加油稿
     * @param usersStnumber
     */
    void deleteUsersStnumber(String usersStnumber);


}
