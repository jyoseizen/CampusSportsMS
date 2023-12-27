package org.glxy.sass.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.glxy.sass.config.Result;
import org.glxy.sass.entity.College;
import org.glxy.sass.entity.Users;
import org.glxy.sass.mapper.CollegeMapper;
import org.glxy.sass.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学院管理
 */
@Api
@RestController
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    CollegeService collegeService;

    @Autowired
    UsersService usersService;

    @Autowired
    RoleService roleService;

    @Autowired
    DraftService draftService;

    @Autowired
    ApplicationformService applicationformService;

    @Autowired
    EnrollService enrollService;

    @Resource
    CollegeMapper collegeMapper;

    /**
     * 查询学院全部
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
        LambdaQueryWrapper<College> wrapper = Wrappers.<College>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(College::getCollegeNumber, search);
//           wrapper.like(Student::getStuGrade,search);
        }
//        Wrappers.<Book> lambdaQuery().like(Book::getNickName,search)
        Page<College> collegePage = collegeMapper.selectPage(new Page<>(PageNum, PageSize), wrapper);
        return Result.success(collegePage);
    }

    /**
     * 学院增加
     *
     * @param college
     * @return
     */
    @PostMapping("/insertCollege")
    public Result<?> insert(College college) {  //加@RequestBody会不兼容
        College collegeNb = collegeMapper.selectOne(Wrappers.<College>lambdaQuery().eq(College::getCollegeNumber, college.getCollegeNumber()));
        College collegeNe = collegeMapper.selectOne(Wrappers.<College>lambdaQuery().eq(College::getCollegeName, college.getCollegeName()));
        if (collegeNb != null) {
            return Result.error("-1", "学院编号已存在");
        }
        if (collegeNe != null) {
            return Result.error("-2", "学院名称已存在");
        }
        collegeMapper.insert(college);
        System.out.println("222");
        return Result.success();
    }

    /**
     * 学院删除
     *
     * @param collegeName
     * @return
     */
    @GetMapping("/deleteCollege")
    public Result<?> delete(String collegeName) {
//        List map = collegeService.selectUsersCollegeName(collegeName);  //查询用户列表
        List<Users> iusers = usersService.selectUsersCollege(collegeName);
        collegeService.deleteCollege(collegeName);  //删除学院
        for (int i=0;i<iusers.size();i++){
            System.out.println(i+"  "+iusers.get(i).getUsersStnumber());
            usersService.delete(iusers.get(i).getUsersStnumber());  //删除用户
            roleService.deleteByUsersStnumber(iusers.get(i).getUsersStnumber());  //删除角色
            draftService.deleteUsersStnumber(iusers.get(i).getUsersStnumber());  //删除加油稿
            applicationformService.deleteAppUsersStnumber(iusers.get(i).getUsersStnumber());  //删除报名记录
            enrollService.deleteEnUsersStnumber(iusers.get(i).getUsersStnumber());   //删除报名名单
        }
        return Result.success();
    }

    /**
     * 学院修改
     *
     * @param collegeN
     * @return
     */
    @PutMapping("/updateCollege")
    public Result<?> update(College collegeN) {
        collegeMapper.updateById(collegeN);
        return Result.success();
    }

    /**
     * 查询学院名字（动态字典）
     * @return
     */
    @GetMapping("/selectCollegeName")
    public List<College> selectCollegeName(){
        System.out.println("调用数据字典");
        return collegeMapper.selectCollegeName();
    }

    /**
     * 以学院名查询用户
     * @param collegeName
     * @return
     */
    @GetMapping("/selectUsersCollegeName")
    public List selectUsersCollegeName(String collegeName){
        return collegeService.selectUsersCollegeName(collegeName);
    }
}
