package com.fxtahe.fxblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @program fxblog-boot
 * @description 启动器
 * @author fxtahe
 * @create 2020/04/14
 */
@EnableCaching
@SpringBootApplication
public class FxblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FxblogApplication.class,args);
    }
}
