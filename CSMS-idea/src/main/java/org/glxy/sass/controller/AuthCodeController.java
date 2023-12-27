package org.glxy.sass.controller;

import org.glxy.sass.config.Result;
import org.glxy.sass.service.UsersService;
import org.glxy.sass.utils.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Random;

/**
 * 验证码
 */
@RestController
@RequestMapping("/authCode")
public class AuthCodeController {
    @Autowired
    UsersService usersService;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String from;

    private static String EmailCode = null;

    /**
     * 登陆验证码
     * @param response
     * @param request
     * @param code
     * @throws Exception
     */
    @GetMapping("/code")
    public void getCode(HttpServletResponse response, HttpServletRequest request, String code) throws Exception {
        HttpSession session = request.getSession();
        //利用图片工具生成图片
        Object[] objs = CodeUtils.createImage(code);
        //将验证码存入Session
        session.setAttribute("imageCode", objs[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[0];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    /**
     * 邮箱验证码
     *
     * @param usersStnumber
     * @return
     */
    @GetMapping("/getEmail")
    public Result<?> getEmail(String usersStnumber, String usersEmail) {

        String iemail = String.valueOf(usersService.selectUsersEmail(usersStnumber, usersEmail));
        if (iemail == "[]") {
            return Result.error("邮箱错误");
        } else {
            EmailCode = String.format("%04d", new Random().nextInt(9999));
            System.out.println("随机数" + EmailCode);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("【运动会报名系统】");
            message.setText("用户 " + usersStnumber + "正在重置密码，验证码为：" + EmailCode + "。请勿将验证码告知他人");
//        message.setFrom("714152865@qq.com");
            message.setFrom(from);
            message.setTo(usersEmail);
            mailSender.send(message);
//        javaMailSender.send(message);
            return Result.success();
        }
    }

    /**
     * 重置密码
     *
     * @param usersStnumber
     * @param usersPwd
     * @param emailCode
     * @return
     */
    @GetMapping("/updatePwd")
    public Result<?> updatePwd(String usersStnumber, String usersPwd, String emailCode) {
        System.out.println("验证码："+EmailCode);
        System.out.println("验证码2："+emailCode);
        if (Objects.equals(emailCode, EmailCode)) {   //Objects.equals字符对比
            usersService.updatePwd(usersStnumber, usersPwd);
            EmailCode = null;    //初始化验证码
            return Result.success();
        } else {
            return Result.error("验证码错误");
        }
    }
}
