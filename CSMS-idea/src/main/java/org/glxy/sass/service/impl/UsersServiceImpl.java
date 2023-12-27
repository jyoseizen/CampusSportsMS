package org.glxy.sass.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.entity.Users;
import org.glxy.sass.mapper.UsersMapper;
import org.glxy.sass.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Resource
    UsersMapper usersMapper;

//    @Override
//    public List<Users> listAll() {
//        return usersMapper.listAll();
//    }

//    @Override
//    public List<Users> selectBySn(Integer PageNum,Integer PageSize,String usersStnumber,String usersName){
//        return  usersMapper.selectBySn(PageNum,PageSize,usersStnumber,usersName);
//    }

    @Override
    public List loginSelect(String usersStnumber, String usersPwd) {
        return usersMapper.loginSelect(usersStnumber, usersPwd);
    }

    /**
     * 新增用户（文件导入专用）
     *
     * @param users
     */
    @Override
    public void add(Users users) {
        usersMapper.add(users);
    }

    /**
     * 用户上传头像（图片）
     *
     * @param users
     */
    @Override
    public void updateUrl(Users users) {
        usersMapper.updateUrl(users);
    }

    /**
     * 用户名字，学号，院系模糊查询
     *
     * @param page
     * @param usersStnumber
     * @param usersName
     * @param collegeName
     * @return
     */
    @Override
    public Page<Users> selectBySn(Page<Users> page,
                                  @Param("usersStnumber") String usersStnumber,
                                  @Param("usersName") String usersName,
                                  @Param("collegeName") String collegeName) {
        return usersMapper.selectBySn(page, usersStnumber, usersName, collegeName);
    }

    /**
     * 用户删除
     *
     * @param usersStnumber
     */
    @Override
    public void delete(@Param("usersStnumber") String usersStnumber) {
        usersMapper.delete(usersStnumber);
    }

    /**
     * 用户修改个人信息
     *
     * @param usersPhone
     * @param usersPwd
     */
    @Override
    public void updateUsersPwd(@Param("usersStnumber") String usersStnumber,
                               @Param("usersPhone") String usersPhone,
                               @Param("usersPwd") String usersPwd,
                               @Param("usersEmail") String usersEmail) {
        usersMapper.updateUsersPwd(usersStnumber, usersPhone, usersPwd, usersEmail);
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void deleteIds(String ids) {
        usersMapper.deleteIds(ids);
    }

    /**
     * 查询用户性别
     *
     * @param usersStnumber
     */
    @Override
    public Integer selectByUsersSex(String usersStnumber) {
        return usersMapper.selectByUsersSex(usersStnumber);
    }

    /**
     * 查询用户头像
     *
     * @param usersStnumber
     * @return
     */
    @Override
    public String selectByUsersUrl(String usersStnumber) {
        return usersMapper.selectByUsersUrl(usersStnumber);
    }

    //    /**
//     * 以学院名查询用户
//     * @param collegeName
//     * @return
//     */
    @Override
    public List<Users> selectUsersCollege(String collegeName) {
        return usersMapper.selectUsersCollege(collegeName);
    }

    /**
     * 查询用户邮箱是否对应
     * @param usersStnumber
     * @param usersEmail
     * @return
     */
    @Override
    public List selectUsersEmail(String usersStnumber, String usersEmail){
        return usersMapper.selectUsersEmail(usersStnumber, usersEmail);
    }

    /**
     * 修改密码(重置密码)
     * @param usersStnumber
     * @param usersPwd
     */
    @Override
    public void updatePwd(String usersStnumber,String usersPwd){
        usersMapper.updatePwd(usersStnumber, usersPwd);
    }

}
