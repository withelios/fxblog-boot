package com.fxtahe.fxblog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxtahe.fxblog.vo.wrapper.ResponseStatus;
import com.fxtahe.fxblog.vo.wrapper.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* <p>
 *     当访问需要权限接口时未进行认证处理，例如无TOKEN或Token过期失效处理
* </p>
* @author fxtahe
* @date 2020/6/18
*/
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(Result.of(ResponseStatus.AUTHENTICATE_ERROR)));
        response.getWriter().flush();
    }
}
