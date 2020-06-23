package com.fxtahe.fxblog.service;

import java.util.Map;

/**
* @description 认证服务
* @author fxtahe
* @date 2020/6/18
*/
public interface AuthService {

    Map<String,String> refresh(String refreshToken);
}
