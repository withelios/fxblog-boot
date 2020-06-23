package com.fxtahe.fxblog.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fxtahe.fxblog.service.AuthService;
import com.fxtahe.fxblog.util.Const;
import com.fxtahe.fxblog.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
* @description 认证服务实现接口
* @author fxtahe
* @date 2020/6/18
*/
@Slf4j
@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Map<String, String> refresh(String token) {
        try{
            if(!StringUtils.isBlank(token)&& token.startsWith(Const.TOKEN_PREFIX)) {
                String refreshToken = token.replaceFirst(Const.TOKEN_PREFIX,"");
                log.info("refresh token:{}", refreshToken);
                if (jwtTokenUtil.isReFreshToken(refreshToken)) {
                    Map<String, String> refresh = new HashMap<>(2);
                    String newRefreshToken = jwtTokenUtil.resetRefreshToken(refreshToken);
                    String newAccessToken = jwtTokenUtil.resetAccessToken(refreshToken);
                    refresh.put(Const.ACCESS_TOKEN, newAccessToken);
                    refresh.put(Const.REFRESH_TOKEN, newRefreshToken);
                    return refresh;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }
}
