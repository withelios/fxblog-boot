package com.fxtahe.fxblog.controller;

import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.service.AuthService;
import com.fxtahe.fxblog.util.Const;
import com.fxtahe.fxblog.vo.wrapper.ResponseStatus;
import com.fxtahe.fxblog.vo.wrapper.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
* @description 注册，token刷新控制器
* @author fxtahe
* @date 2020/6/18
*/
@Slf4j
@ResponseWrapper
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;



    /**
     * 刷新Token
     * @param token
     * @return
     */
    @GetMapping("/refresh")
    public Result refreshToken(@RequestHeader(Const.TOKEN_HEADER) String token){
        log.info("---------refresh token-----------");
        Map<String, String> refresh= authService.refresh(token);
        return refresh == null ?Result.of(ResponseStatus.REFRESH_TOKEN_ERROR) : Result.of(ResponseStatus.OK,refresh);
    }

}
