package com.fxtahe.fxblog.config.exception;

import com.fxtahe.fxblog.vo.wrapper.ResponseStatus;
import com.fxtahe.fxblog.vo.wrapper.Result;
import lombok.extern.slf4j.Slf4j;
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

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage(),e);
        Result.failure();
        return new Result(ResponseStatus.INTERNAL_SERVER_ERROR);
    }

}
