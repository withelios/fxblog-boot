package com.fxtahe.fxblog.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxtahe.fxblog.entity.Author;
import com.fxtahe.fxblog.entity.Role;
import com.fxtahe.fxblog.mapper.AuthorMapper;
import com.fxtahe.fxblog.mapper.RoleMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @description  UserDetailServiceImpl
* @author fxtahe
* @date 2020/6/17
*/
@Component("userDetailsServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {


    @Resource
    private AuthorMapper authorMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = authorMapper.selectOne(new QueryWrapper<Author>().eq("author_name", username));
        if(author == null){
            throw new UsernameNotFoundException("username not found");
        }
        List<Role> roles = roleMapper.selectRoleByUserId(author.getId());
        return new UserDetailsImpl(author,roles.stream().map(Role::getRole).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
//        return new User(author.getAuthorName(),author.getPassword()
//                ,roles.stream().map(Role::getRole).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
