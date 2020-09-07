package com.fxtahe.fxblog.config.interceptor;

import com.fxtahe.fxblog.service.ArticleService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ViewArticleInterceptor implements HandlerInterceptor {

    @Resource
    private ArticleService articleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        return false;
    }
}
