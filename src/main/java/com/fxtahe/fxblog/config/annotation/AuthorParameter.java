package com.fxtahe.fxblog.config.annotation;

import com.fxtahe.fxblog.util.Const;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     当不同权限及不同ID用户访问资源接口时，可能需要判断是否需要传入用户名参数
 *     比如 Admin用户不需要传入用户名，而author用户需要传入用户名
 * </p>
 * @author fxtahe
 * @date 2020/6/19
 */
@Target({ ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorParameter {

    String value() default Const.PARAM_ID;
}
