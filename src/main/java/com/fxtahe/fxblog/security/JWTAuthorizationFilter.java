package com.fxtahe.fxblog.security;

import com.fxtahe.fxblog.entity.Author;
import com.fxtahe.fxblog.util.Const;
import com.fxtahe.fxblog.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author fxtahe
 * @description JWT 认证过滤器
 * @date 2020/6/16
 */
@Slf4j
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource(name="handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    private List<AntPathRequestMatcher> shouldNotFilterMaters;

    private RequestHeaderRequestMatcher requiresAuthenticationRequestMatcher;

    public JWTAuthorizationFilter() {
        //拦截header中带Authorization的请求
        this.requiresAuthenticationRequestMatcher = new RequestHeaderRequestMatcher(Const.TOKEN_HEADER);
        this.shouldNotFilterMaters = Arrays.stream(new String[]{Const.ADMIN_AUTH_URL, Const.AUTHOR_AUTH_URL}).map(AntPathRequestMatcher::new).collect(Collectors.toList());
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return shouldNotFilterMatch(this.shouldNotFilterMaters, request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (!requiresAuthentication(request)) {
                doFilter(request, response, filterChain);
                return;
            }
            String tokenHeader = request.getHeader(Const.TOKEN_HEADER);
            if (tokenHeader != null && tokenHeader.startsWith(Const.TOKEN_PREFIX)) {
                String token = tokenHeader.replaceFirst(Const.TOKEN_PREFIX, "");
                jwtTokenUtil.isTokenExpired(token);
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
            }
            doFilter(request, response, filterChain);
        } catch (JwtException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            resolver.resolveException(request, response, null, e);
        }
    }

    protected boolean requiresAuthentication(HttpServletRequest request) {
        return requiresAuthenticationRequestMatcher.matches(request);
    }

    protected boolean shouldNotFilterMatch(List<AntPathRequestMatcher> matchers, HttpServletRequest request) {
        return matchers.stream().noneMatch(matcher -> matcher.matches(request));
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Integer id = jwtTokenUtil.getIdFromToken(token);
            String username = jwtTokenUtil.getUsernameFromToken(token);

            List<String> roles = jwtTokenUtil.getRoleClaimsFromToken(token);
            Set<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            if (!StringUtils.isEmpty(username)) {
                UserDetailsImpl userDetailsImpl = new UserDetailsImpl(new Author().setId(id).setAuthorName(username), authorities);
                return new UsernamePasswordAuthenticationToken(userDetailsImpl, null, authorities);
            }
            return null;
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }

}
