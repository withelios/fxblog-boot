package com.fxtahe.fxblog.config.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 操作日志注解
 * </p>
 *
 * @author fxtahe
 * @since 2020/8/19
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationLog {

    String operationModule() default "";

    String operationType() default "";

    String operationDesc() default "";
}
