package org.glxy.sass.config;

import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForStyle;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Sa-Token的路由拦截器
        registry.addInterceptor(new SaRouteInterceptor((req, res, handler) -> {

            // 登录认证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
            SaRouter.match("/**", "/login/doLogin", r -> StpUtil.checkLogin());

            // 角色认证 -- 拦截路由
            //用户类
            SaRouter.match("/users/search", r -> StpUtil.checkRoleOr("admin","superAdmin"));
//            SaRouter.match("/users/selectBySn", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/users/updateUsers", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/users/insertUsers", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/users/deleteUsers", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/users/updateUsersPwd", r -> StpUtil.checkRoleOr("admin","superAdmin","user"));
            SaRouter.match("/users/upload", r -> StpUtil.checkRoleOr("admin","superAdmin","user"));
            SaRouter.match("/users/uploadImg", r -> StpUtil.checkRoleOr("admin","superAdmin","user"));

            //比赛实体
            SaRouter.match("/competition/search", r -> StpUtil.checkRoleOr("admin","superAdmin","user"));
            SaRouter.match("/competition/selectByNb", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/competition/insertCompetition", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/competition/updateScore", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/competition/deleteCompetition", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/competition/addCompetition", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/competition/updateCompetition", r -> StpUtil.checkRoleOr("admin","superAdmin","user"));
            SaRouter.match("/competition/addCompetition", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/competition/addCompetition", r -> StpUtil.checkRoleOr("admin","superAdmin"));

            //报名表
            SaRouter.match("/enroll/selectEnAll", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/enroll/selectBySber", r -> StpUtil.checkRoleOr("admin","superAdmin","user"));
            SaRouter.match("/enroll/getlist", r -> StpUtil.checkRoleOr("admin","superAdmin","user"));
            SaRouter.match("/enroll/updateCompetition", r -> StpUtil.checkRoleOr("admin","superAdmin","user"));

            //场地
            SaRouter.match("/site/search", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/site/insertSite", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/site/updateSite", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/site/deleteSite", r -> StpUtil.checkRoleOr("admin","superAdmin"));

            //学院
            SaRouter.match("/college/search", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/college/insertCollege", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/college/deleteCollege", r -> StpUtil.checkRoleOr("admin","superAdmin"));
            SaRouter.match("/college/updateCollege", r -> StpUtil.checkRoleOr("admin","superAdmin"));

            //加油稿修改状态
            SaRouter.match("/draft/updateState", r -> StpUtil.checkRoleOr("admin","superAdmin"));

        }))
                //需要拦截的路径
                .addPathPatterns("/**")
                // 不需要拦截的路径
                .excludePathPatterns(
                        //文档路径
                        "/doc.html", "/webjars/**", "/v2/api-docs/**", "/swagger-resources/**", "/favicon.ico",
                        //静态资源路径
                        "/image/**",
                        //验证码路径
                        "/authCode/**",
                        //邮箱验证码路径
                        "/login/getEmail"
                )
        ;
    }
    // Sa-Token 整合 jwt (Style模式)
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForStyle();
    }

    @Bean
    public SaServletFilter getSaServletFilter(){
        return new SaServletFilter()

                // 指定 [拦截路由] 与 [放行路由]
                .addInclude("/**")
                .setError(e ->{
                    System.out.println("----token异常");
                    return Result.error("401","异常");
                });
    }
}
