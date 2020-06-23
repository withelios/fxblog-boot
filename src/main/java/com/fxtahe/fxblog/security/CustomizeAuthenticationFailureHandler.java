package com.fxtahe.fxblog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxtahe.fxblog.vo.wrapper.ResponseStatus;
import com.fxtahe.fxblog.vo.wrapper.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @description 定制登陆失败认证Handler
* @author fxtahe
* @date 2020/6/17
*/
@Component("customizeAuthenticationFailureHandler")
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(Result.of(ResponseStatus.AUTHENTICATE_ERROR)));
        response.getWriter().flush();
    }
}
