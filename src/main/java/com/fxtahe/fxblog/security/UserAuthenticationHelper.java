package com.fxtahe.fxblog.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
* @description 用户信息辅助类
* @author fxtahe
* @date 2020/6/19
*/
public class UserAuthenticationHelper {

    /**
     * 获取当前认证信息
     * @return Authentication
     */
    public static Authentication getCurrentAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前用户信息
     * @return Principal
     */
    public static Object getCurrentPrincipal(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
