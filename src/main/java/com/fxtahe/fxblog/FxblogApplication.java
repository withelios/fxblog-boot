package com.fxtahe.fxblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @program fxblog-boot
 * @description 启动器
 * @author fxtahe
 * @create 2020/04/14
 */
@MapperScan("com.fxtahe.fxblog.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FxblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FxblogApplication.class,args);
    }
}
