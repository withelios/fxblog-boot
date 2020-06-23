package com.fxtahe.fxblog.config.exception;

import com.fxtahe.fxblog.vo.wrapper.ResponseStatus;
import com.fxtahe.fxblog.vo.wrapper.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program fxblog-boot
 * @description 全局异常处理器
 * @author fxtahe
 * @create 2020/04/14
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e){
        log.error(e.getMessage(),e);
        return Result.failure(e.getMsg());
    }

    /**
     * 异常处理通用类
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage(),e);
        return Result.failure();
    }

    /**
     * 登陆失败
     * @param e
     * @return Result
     */
    @ExceptionHandler(value={UsernameNotFoundException.class})
    public Result handleUsernameNotFoundException(UsernameNotFoundException e){
        log.error(e.getMessage(),e);
        return Result.of(ResponseStatus.LOGIN_ERROR);
    }

    /**
     * 认证失败权限类
     * @param e
     * @return Result
     */
    @ExceptionHandler(value={AuthenticationException.class})
    public Result handleAuthenticationException(AuthenticationException e){
        log.error(e.getMessage(),e);
        return Result.of(ResponseStatus.AUTHENTICATE_ERROR);
    }

    /**
     * Token 无效处理
     * @param e
     * @return Result
     */
    @ExceptionHandler(value = {JwtException.class})
    public Result handleJwtException(JwtException e){
        log.warn(e.getMessage(),e);
        return Result.of(ResponseStatus.ILLEGAL_TOKEN);
    }

    /**
     * Token 过期处理
     * @param e
     * @return Result
     */
    @ExceptionHandler(value = {ExpiredJwtException.class})
    public Result handleExpiredJwtException(ExpiredJwtException e){
        log.warn(e.getMessage(),e);
        return Result.of(ResponseStatus.TOKEN_EXPIRED);
    }
}
