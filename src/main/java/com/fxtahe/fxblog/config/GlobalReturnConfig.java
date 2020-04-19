package com.fxtahe.fxblog.config;

import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.vo.wrapper.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @program fxblog-boot
 * @description 全局返回配置
 * @author fxtahe
 * @create 2020/04/14
 */
@Configuration
public class GlobalReturnConfig {


    @RestControllerAdvice(annotations = ResponseWrapper.class)
    static class ResultResponseAdvice implements ResponseBodyAdvice<Object> {
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(Object body,
                                      MethodParameter methodParameter,
                                      MediaType mediaType,
                                      Class<? extends HttpMessageConverter<?>> aClass,
                                      ServerHttpRequest serverHttpRequest,
                                      ServerHttpResponse serverHttpResponse) {
            if (MediaType.APPLICATION_JSON.equals(mediaType)) {
                if (body instanceof Result) {
                    return body;
                } else {
                    return Result.success(body);
                }
            }
            // 非JSON格式body直接返回
            return body;
        }


    }

}
