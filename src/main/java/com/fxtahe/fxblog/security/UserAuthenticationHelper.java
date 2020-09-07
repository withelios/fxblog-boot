package com.fxtahe.fxblog.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 获取当前用户ID
     * @return
     */
    public static Integer getCurrentUserId(){
        UserDetailsImpl userDetails = (UserDetailsImpl) getCurrentPrincipal();
        return userDetails.getAuthorId();
    }

    /**
     * 获取当前用户名
     * @return
     */
    public static String getCurrentUserName(){
        UserDetailsImpl userDetails = (UserDetailsImpl) getCurrentPrincipal();
        return userDetails.getUsername();
    }

    /**
     * 获取当前用户权限列表
     * @return
     */
    public static List<String> getCurrentUserAuthorities(){
        UserDetailsImpl userDetails = (UserDetailsImpl) getCurrentPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
