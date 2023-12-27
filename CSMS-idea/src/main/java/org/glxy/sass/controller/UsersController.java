package org.glxy.sass.controller;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.glxy.sass.config.Result;
import org.glxy.sass.entity.Role;
import org.glxy.sass.entity.Users;
import org.glxy.sass.mapper.UsersMapper;
import org.glxy.sass.service.*;
import org.glxy.sass.utils.EasyPoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 用户信息
 */
@Api
@Slf4j
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;

    @Autowired
    RoleService roleService;

    @Autowired
    DraftService draftService;

    @Autowired
    EnrollService enrollService;

    @Autowired
    ApplicationformService applicationformService;



    //    private final String EmailCode = String.format("%04d",new Random().nextInt(9999)); //随机验证码


//    @Autowired
//    private JavaMailSenderImpl javaMailSender;

//    @Autowired
//    private SimpleMailMessage simpleMailMessage;

    @Resource
    UsersMapper usersMapper;

    @GetMapping("/sssss")
    public void login3() {

        StpUtil.logout();

//        return Result.success(StpUtil.logout());
    }

    /**
     * 用户信息分页查询
     *
     * @param PageNum
     * @param PageSize
     * @param search
     * @return
     */
//    @SaCheckRole("super-admin")
    @GetMapping("/search")
    public Result<?> findPage1(@RequestParam(defaultValue = "1") Integer PageNum,
                               @RequestParam(defaultValue = "100") Integer PageSize,
                               @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Users> wrapper = Wrappers.<Users>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Users::getUsersStnumber, search);
//           wrapper.like(Student::getStuGrade,search);
        }
//        Wrappers.<Book> lambdaQuery().like(Book::getNickName,search)
        Page<Users> classPage = usersMapper.selectPage(new Page<>(PageNum, PageSize), wrapper);
        return Result.success(classPage);
    }

    /**
     * 用户名字，学号，院系模糊查询
     *
     * @param PageNum
     * @param PageSize
     * @param usersStnumber
     * @param usersName
     * @param collegeName
     * @return
     */
    @GetMapping("/selectBySn")
    public Result<?> selectBySn(@RequestParam(defaultValue = "1") Integer PageNum,
                                @RequestParam(defaultValue = "20") Integer PageSize,
                                String usersStnumber, String usersName, String collegeName) {
        Page<Users> usersPage = usersService.selectBySn(new Page<>(PageNum, PageSize), usersStnumber, usersName, collegeName);
        return Result.success(usersPage);
    }

    /**
     * 用户修改
     */
    @PutMapping("/updateUsers")
    public Result<?> update(Users us) {
        usersMapper.updateById(us);
        return Result.success();
    }

    /**
     * 新增
     *
     * @param users
     * @return
     */
    @PostMapping("/insertUsers")
    public Result<?> insert(@RequestBody Users users) {
        Users usersN = usersMapper.selectOne(Wrappers.<Users>lambdaQuery().eq(Users::getUsersStnumber, users.getUsersStnumber()));
        if (usersN != null) {
            return Result.error("-1", "学号已存在");
        }
        Role role = new Role();
        role.setUsersStnumber(users.getUsersStnumber());
        role.setUsersName(users.getUsersName());
        role.setRoleValue("user");
        roleService.addR(role);
        usersMapper.insert(users);
//        System.out.println(role);
//        System.out.println(users);
        return Result.success();
    }


    /**
     * 根据用户学号删除
     *
     * @param usersStnumber
     * @return
     */
    @DeleteMapping("/deleteUsers")
    public Result<?> delete(String usersStnumber) {
        String dirPath = System.getProperty("user.dir")+"/src/main/resources/static";
        String imgUrl = usersService.selectByUsersUrl(usersStnumber);
        String imgPath = dirPath + "/" + imgUrl;
        System.out.println("图片" + imgPath);
        File imgFile = new File(imgPath);
        if (!imgUrl.equals("默认头像.jpg")) {
            imgFile.delete();  //删除用户原头像
        }
        usersService.delete(usersStnumber);
        roleService.deleteByUsersStnumber(usersStnumber);  //删除角色
        draftService.deleteUsersStnumber(usersStnumber);  //删除加油稿
        applicationformService.deleteAppUsersStnumber(usersStnumber);  //删除报名记录
        enrollService.deleteEnUsersStnumber(usersStnumber);   //删除报名名单
        return Result.success();
    }

    /**
     * 用户修改个人信息
     *
     * @param usersStnumber
     * @param usersPhone
     * @param usersPwd
     * @return
     */
    @GetMapping("/updateUsersPwd")
    public Result<?> updateUsersPwd(@RequestParam String usersStnumber,
                                    @RequestParam String usersPhone,
                                    @RequestParam String usersPwd,
                                    @RequestParam String usersEmail) {
        usersService.updateUsersPwd(usersStnumber, usersPhone, usersPwd, usersEmail);
        return Result.success();
    }

    /**
     * 文件导入
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<?> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file == null) {
            log.info("file 无数据");
            return Result.success("导入失败");
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(1);  //标题行
        params.setHeadRows(1);   //表头行
        String pwd = SaSecureUtil.md5("123456");
        try {
            List<Users> list = EasyPoiUtil.importExcel(file, 1, 1, Users.class);
            for (Users users : list) {
                Users iusers = new Users();
                Role irole = new Role();

                irole.setUsersStnumber(users.getUsersStnumber());
                irole.setUsersName(users.getUsersName());
                irole.setRoleValue("user");
                roleService.addR(irole);

                iusers.setUsersName(users.getUsersName());
                iusers.setUsersStnumber(users.getUsersStnumber());
                iusers.setUsersSex(users.getUsersSex());
                iusers.setUsersPhone(users.getUsersPhone());
                iusers.setUsersPwd(pwd);  //给默认密码
                iusers.setCollegeName(users.getCollegeName());
//                iusers.setCompetitionNb(users.getCompetitionNb());
                iusers.setUsersUrl("默认头像.jpg");  //默认头像
                usersService.add(iusers);
                System.out.println("===>" + iusers);
            }
            return Result.success("导入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("导入失败");
        }
//        return Result.success("导入成功");
    }

    /**
     * 上传头像
     *
     * @param picFile
     * @param usersStnumber
     * @return
     */
    @PostMapping("/uploadImg")
    public Result<?> uploadImg(@RequestParam("file") MultipartFile picFile,
                               @RequestParam String usersStnumber) {
        String dirPath = "D:/高级技术视频/52企业财务/运动会管理系统/BYSJ.SASS/src/main/resources/static";
        String imgUrl = usersService.selectByUsersUrl(usersStnumber);
        String imgPath = dirPath + "/" + imgUrl;
        System.out.println("图片" + imgPath);
        File imgFile = new File(imgPath);
        if (!imgUrl.equals("默认头像.jpg")) {
            imgFile.delete();  //删除用户原头像
        }
        String fileName = picFile.getOriginalFilename(); //获取原文件名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffix; //唯一文件名
        File dirFile = new File(dirPath);  //创建一个file对象
        Users iusers = new Users();
        if (!dirFile.exists()) {
            dirFile.mkdir();  //没有这个文件夹就创建一个
        }
        try {
            String filePath = dirPath + "/" + fileName;
            picFile.transferTo(new File(filePath));  //保存到文件中
            iusers.setUsersStnumber(usersStnumber);
            iusers.setUsersUrl(fileName);
            usersService.updateUrl(iusers);
            return Result.success("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("上传失败");
        }

    }

//    /**
//     * 批量删除
//     * @param ids
//     */
//    @GetMapping("/deleteIds")
//    public Result<?> deleteIds(String ids){
//        usersService.deleteIds(Convert.toStrArray(ids));
//        return Result.success();
//    }

    /**
     * 查询用户性别
     *
     * @param usersStnumber
     * @return
     */
    @GetMapping("/selectByUsersSex")
    public Integer selectByUsersSex(String usersStnumber) {
        return usersService.selectByUsersSex(usersStnumber);
    }


    /**
     * 查询用户头像
     *
     * @param usersStnumber
     * @return
     */
    @GetMapping("/selectByUsersUrl")
    public String selectByUsersUrl(String usersStnumber) {
        return usersService.selectByUsersUrl(usersStnumber);
    }

    @GetMapping("/selectUsersCollege")
    public List<Users> selectUsersCollege(String collegeName) {
//        System.out.println(getEmail());
        return usersService.selectUsersCollege(collegeName);
    }




//    @PostMapping("/updateUrl2")
//    public Result<?> updateUrl(Users users){
//        usersService.updateUrl(users);
//        return Result.success("成功");
//    }

//    /**
//     * 新增用户（文件导入专用）
//     * @param users
//     */
//    @PostMapping("/add")
//    public Result<?> add(Users users) {
//        usersService.add(users);
//        return Result.success("成功");
//    }


}
