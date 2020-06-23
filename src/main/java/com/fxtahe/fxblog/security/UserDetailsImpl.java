package com.fxtahe.fxblog.security;

import com.fxtahe.fxblog.entity.Author;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
* @description 自定义 UserDetails
* @author fxtahe
* @date 2020/6/16
*/
@Data
public class UserDetailsImpl implements UserDetails {

    private Author author;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Author author,Collection<? extends GrantedAuthority> authorities){
        this.author = author;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getAuthorId(){return author.getId();}
    @Override
    public String getPassword() {
        return author.getPassword();
    }

    @Override
    public String getUsername() {
        return author.getAuthorName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
