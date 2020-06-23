package com.fxtahe.fxblog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxtahe.fxblog.util.Const;
import com.fxtahe.fxblog.util.JwtTokenUtil;
import com.fxtahe.fxblog.vo.wrapper.ResponseStatus;
import com.fxtahe.fxblog.vo.wrapper.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @description 定制登陆成功认证Handler
* @author fxtahe
* @date 2020/6/17
*/
@Component("customizeAuthenticationSuccessHandler")
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        Integer id = principal.getAuthorId();
        String authorName = principal.getUsername();
        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
        List<String> role = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String accessToken = jwtTokenUtil.generateAccessToken(id,authorName, role);
        String refreshToken = jwtTokenUtil.generateRefreshToken(id,authorName, role);
        Map<String,String> res = new HashMap<>(2);
        res.put(Const.ACCESS_TOKEN,accessToken);
        res.put(Const.REFRESH_TOKEN,refreshToken);
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(Result.of(ResponseStatus.OK,res)));
        response.getWriter().flush();
    }
}
