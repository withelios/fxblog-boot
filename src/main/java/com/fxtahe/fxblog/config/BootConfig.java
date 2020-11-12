package com.fxtahe.fxblog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.security.AuthorMethodArgumentResolver;
import com.fxtahe.fxblog.vo.wrapper.Result;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
* @description Fxblog-Boot Configure
* @author fxtahe
* @date 2020/6/12
*/
@EnableCaching
@EnableTransactionManagement
@Configuration
@MapperScan("com.fxtahe.fxblog.mapper")
public class BootConfig {


    /**
     * 跨域处理
     * @return WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer corsConfig(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true)
                        .maxAge(3600)
                        .allowedHeaders("*");
            }
            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
                resolvers.add(new AuthorMethodArgumentResolver());
            }
        };
    }

    /**
     * 全局响应包装配置
     */
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

    /**
     * MybatisPlus 分页插件配置
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setLimit(10);
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }


    /**
     * 缓存自定义key生成器
     * @return KeyGenerator
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(method.getReturnType().getName()).append(".");
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

}
