package org.glxy.sass.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.Draft;
import org.glxy.sass.mapper.DraftMapper;
import org.glxy.sass.service.DraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DraftServiceImpl extends ServiceImpl<DraftMapper, Draft> implements DraftService {

    @Resource
    DraftMapper draftMapper;

    /**
     * 加油稿状态修改
     * @param draft
     */
    @Override
    public void updateState(Draft draft) {
        draftMapper.updateState(draft);
    }

    @Override
    public Page<Draft> selectBySm(Page<Draft> page,
                                  @Param("usersStnumber") String usersStnumber,
                                  @Param("usersName")String usersName,
                                  @Param("collegeName")String collegeName){
        return draftMapper.selectBySm(page,usersStnumber,usersName,collegeName);
    }

    /**
     * 用户查询审核通过加油稿（一对多）
     * @param page
     * @return
     */
    @Override
    public Page<Draft> selectImgUrl(Page<Draft> page){
        return draftMapper.selectImgUrl(page);
    }

    /**
     * 根据学号删除加油稿
     * @param usersStnumber
     */
    @Override
    public void deleteUsersStnumber(String usersStnumber){
        draftMapper.deleteUsersStnumber(usersStnumber);
    }
}
