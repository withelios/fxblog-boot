package com.fxtahe.fxblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @program: fxblog-boot
 * @description: 启动器
 * @author: fxtahe
 * @create: 2020-04-07
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FxblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FxblogApplication.class,args);
    }
}
