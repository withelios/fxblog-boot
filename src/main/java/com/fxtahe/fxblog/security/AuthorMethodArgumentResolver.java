package com.fxtahe.fxblog.security;

import com.fxtahe.fxblog.config.annotation.AuthorParameter;
import com.fxtahe.fxblog.util.Const;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Collection;

/**
* @description {@link AuthorParameter}
* @author fxtahe
* @date 2020/6/19
*/
public class AuthorMethodArgumentResolver implements HandlerMethodArgumentResolver {
    

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthorParameter.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Authentication authentication = UserAuthenticationHelper.getCurrentAuthentication();
        if(authentication == null) return null;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream().anyMatch(i -> i.getAuthority().matches(Const.ADMIN_AUTH));
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        AuthorParameter parameterAnnotation = parameter.getParameterAnnotation(AuthorParameter.class);
        assert parameterAnnotation != null;
        String value = parameterAnnotation.value();
        if(isAdmin) return null;
        if(Const.PARAM_AUTHOR.equals(value)){
            return user;
        }
        if(Const.PARAM_NAME.equals(value)){
            return user.getUsername();
        }
        return user.getAuthorId();
    }
}
