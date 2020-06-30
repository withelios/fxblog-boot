package com.fxtahe.fxblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxtahe.fxblog.config.exception.BusinessException;
import com.fxtahe.fxblog.entity.Author;
import com.fxtahe.fxblog.entity.Role;
import com.fxtahe.fxblog.mapper.AuthorMapper;
import com.fxtahe.fxblog.mapper.RoleMapper;
import com.fxtahe.fxblog.service.AuthorService;
import com.fxtahe.fxblog.vo.AuthorVo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fxtahe
 * @since 2020-05-10
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {


    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public void register(Author author) {
        String authorName = author.getLoginName();
        List<Author> authors = baseMapper.selectList(new QueryWrapper<Author>().eq("author_name", authorName));
        if(CollectionUtils.isEmpty(authors)){
            baseMapper.insert(author.setPassword(bCryptPasswordEncoder.encode(author.getPassword())));
        }else{
            throw new BusinessException("用户名已存在");
        }
    }

    @Override
    public AuthorVo getAuthorInfo(Integer id) {
        Author author = baseMapper.selectById(id);
        List<Role> roles = roleMapper.selectRoleByUserId(id);
        AuthorVo authorVo= new AuthorVo();
        BeanUtils.copyProperties(author,authorVo);
        authorVo.setRoles(roles.stream().map(Role::getRole).collect(Collectors.toList()));
        return authorVo;
    }


}
