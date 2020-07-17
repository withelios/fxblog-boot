package com.fxtahe.fxblog.config;

import com.fxtahe.fxblog.security.JWTAuthorizationFilter;
import com.fxtahe.fxblog.util.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;

/**
* @description Security Config
* @author fxtahe
* @date 2020/6/16
*/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name="userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Resource(name="customizeAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Resource(name="customizeAuthenticationFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Resource
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(Const.ADMIN_AUTH_URL).hasAuthority("admin")
                .antMatchers(Const.AUTHOR_AUTH_URL).hasAnyAuthority("admin","author")
                .anyRequest().permitAll()
                .and()
                //.cors().configurationSource(CorsConfigurationSource())
                //.and()
                .csrf().disable()
                .sessionManagement().disable()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and().addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }
    /*跨域原*/
    private CorsConfigurationSource CorsConfigurationSource() {
        UrlBasedCorsConfigurationSource source =  new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");    //同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedMethod("*");    //允许的请求方法，PSOT、GET等
        source.registerCorsConfiguration("/**",corsConfiguration); //配置允许跨域访问的url
        return source;
    }
}
