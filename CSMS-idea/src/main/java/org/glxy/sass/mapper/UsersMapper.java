package org.glxy.sass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.config.Result;
import org.glxy.sass.entity.Users;

import java.util.List;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {

//    List<Users> listAll();

//    List<Users> selectBySn(Integer PageNum,Integer PageSize,String usersStnumber,String usersName);

    /**
     * 验证登陆
     * @param usersStnumber
     * @param usersPwd
     * @return
     */
    List loginSelect(String usersStnumber,String usersPwd);

    /**
     * 新增用户（文件导入专用）
     *
     * @param users
     */
    void add(Users users);

    /**
     * 用户上传头像（图片）
     * @param users
     */
    void updateUrl(Users users);

    /**
     * 用户名字，学号，院系模糊查询
     *
     * @param page
     * @param usersStnumber
     * @param usersName
     * @param collegeName
     * @return
     */

    Page<Users> selectBySn(Page<Users> page,
                           @Param("usersStnumber") String usersStnumber,
                           @Param("usersName") String usersName,
                           @Param("collegeName") String collegeName);


    /**
     * 根据学号删除
     *
     * @param usersStnumber
     */
    void delete(@Param("usersStnumber") String usersStnumber);


    /**
     * 用户修改个人信息
     * @param usersStnumber
     * @param usersPhone
     * @param usersPwd
     */
    void updateUsersPwd(@Param("usersStnumber") String usersStnumber,
                        @Param("usersPhone") String usersPhone,
                        @Param("usersPwd") String usersPwd,
                        @Param("usersEmail") String usersEmail);
//    /**
//     * 统计报名人数（男，女，总  现统计用户表）
//     * @return
//     */
//    Users selectSex();

    /**
     * 批量删除
     * @param ids
     */
    void deleteIds(String ids);

    /**
     * 查询用户性别
     * @param usersStnumber
     */
    Integer selectByUsersSex(String usersStnumber);

    /**
     * 查询用户头像
     * @param usersStnumber
     * @return
     */
    String selectByUsersUrl(String usersStnumber);

//    /**
//     * 以学院名查询用户
//     * @param collegeName
//     * @return
//     */
    List<Users> selectUsersCollege(String collegeName);

    /**
     * 查询用户邮箱是否对应
     * @param usersStnumber
     * @param usersEmail
     * @return
     */
    List selectUsersEmail(String usersStnumber, String usersEmail);

    /**
     * 修改密码(重置密码)
     * @param usersStnumber
     * @param usersPwd
     */
    void updatePwd(String usersStnumber,String usersPwd);



}
