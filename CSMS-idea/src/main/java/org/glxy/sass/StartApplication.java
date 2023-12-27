package org.glxy.sass;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        org.springframework.context.ApplicationContext ac = SpringApplication.run(StartApplication.class, args);
        //启动完成后，自动打开浏览器
        String port = ac.getEnvironment().getProperty("server.port");
        String IPPort = "http://localhost:" + (port == null ? 8080 : port);
        System.out.println("后端启动成功：" + IPPort);
        System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }
}
