package org.glxy.sass.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.glxy.sass.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
//    mybatis-plus 3.4.0废弃了PaginationInterceptor，新版用MybatisPlusInterceptor

//    旧版
//    public PaginationInterceptor paginationInterceptor(){
//        return new PaginationInterceptor();
//    }

//    新版
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
