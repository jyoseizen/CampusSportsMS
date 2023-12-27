package org.glxy.sass.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.config.Result;
import org.glxy.sass.entity.Draft;
import org.glxy.sass.entity.Users;
import org.glxy.sass.mapper.DraftMapper;
import org.glxy.sass.service.DraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api
@RestController
@RequestMapping("/draft")
public class DraftController {

    @Autowired
    DraftService draftService;

    @Resource
    DraftMapper draftMapper;

    /**
     * 查看全部加油稿
     *
     * @param PageNum
     * @param PageSize
     * @param search
     * @return
     */
    @GetMapping("/search")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer PageNum,
                              @RequestParam(defaultValue = "100") Integer PageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Draft> wrapper = Wrappers.<Draft>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Draft::getDraftId, search);
//           wrapper.like(Student::getStuGrade,search);
        }
//        Wrappers.<Book> lambdaQuery().like(Book::getNickName,search)
        Page<Draft> draftPage = draftMapper.selectPage(new Page<>(PageNum, PageSize), wrapper);
        return Result.success(draftPage);
    }

    /**
     * 根据条件模糊查询
     *
     * @param PageNum
     * @param PageSize
     * @param usersStnumber
     * @param usersName
     * @param collegeName
     * @return
     */
    @GetMapping("/selectBySm")
    public Result<?> selectBySm(@RequestParam(defaultValue = "1") Integer PageNum,
                                @RequestParam(defaultValue = "20") Integer PageSize,
                                String usersStnumber, String usersName, String collegeName) {
        Page<Draft> draftPage = draftService.selectBySm(new Page<>(PageNum, PageSize), usersStnumber, usersName, collegeName);
        return Result.success(draftPage);
    }

    /**
     * 用户查询通过审核加油稿（一对多）
     *
     * @param PageNum
     * @param PageSize
     * @return
     */
    @GetMapping("/selectImgUrl")
    public Result<?> selectImgUrl(@RequestParam(defaultValue = "1") Integer PageNum,
                                  @RequestParam(defaultValue = "20") Integer PageSize) {
        Page<Draft> draftImg = draftService.selectImgUrl(new Page<>(PageNum, PageSize));
        return Result.success(draftImg);
    }


    /**
     * 查看通过审核加油稿（DraftState=1 为通过）
     *
     * @param PageNum
     * @param PageSize
     * @param search
     * @return
     */
    @GetMapping("/Usearch")
    public Result<?> findPage2(@RequestParam(defaultValue = "1") Integer PageNum,
                               @RequestParam(defaultValue = "100") Integer PageSize,
                               @RequestParam(defaultValue = "1") String search) {
        LambdaQueryWrapper<Draft> wrapper = Wrappers.<Draft>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Draft::getDraftState, search);
        }
        Page<Draft> draftPage = draftMapper.selectPage(new Page<>(PageNum, PageSize), wrapper);
        System.out.println(draftPage);
        return Result.success(draftPage);
    }

    /**
     * 根据id改状态
     *
     * @param draftId
     * @param draftState
     * @return
     */
    @GetMapping("/updateState")
//    public Result<?> updateState(@RequestBody String parms) {
    public Result<?> updateState(@Param("draftId") Integer draftId,
                                 @Param("draftState") String draftState) {
        System.out.println(draftId + " " + draftState);
        Draft draft = new Draft();
        draft.setDraftId(draftId);
        draft.setDraftState(draftState);
        draftService.updateState(draft);
        return Result.success();
    }

    /**
     * 根据id删除
     *
     * @param draftId
     * @return
     */
    @DeleteMapping("/delete")
    public Result<?> delete(Integer draftId) {
        draftMapper.deleteById(draftId);
        return Result.success();
    }

    /**
     * 增加
     * @param draft
     * @return
     */
    @PostMapping("/insertDraft")
    public Result<?> insert(@RequestBody Draft draft) {
        draftMapper.insert(draft);
        return Result.success();
    }

}
