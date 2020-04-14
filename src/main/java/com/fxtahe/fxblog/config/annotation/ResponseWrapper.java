package com.fxtahe.fxblog.config.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program fxblog-boot
 * @description 响应包装注解
 * @author fxtahe
 * @create 2020/04/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ResponseWrapper {

}
