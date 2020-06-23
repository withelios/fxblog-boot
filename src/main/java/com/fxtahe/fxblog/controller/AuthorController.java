package com.fxtahe.fxblog.controller;


import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Author;
import com.fxtahe.fxblog.security.UserAuthenticationHelper;
import com.fxtahe.fxblog.security.UserDetailsImpl;
import com.fxtahe.fxblog.service.AuthorService;
import com.fxtahe.fxblog.vo.AuthorVo;
import com.fxtahe.fxblog.vo.wrapper.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fxtahe
 * @since 2020-05-10
 */
@ResponseWrapper
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Resource
    private AuthorService authorService;

    @PostMapping("/register")
    public Result register(@Valid Author author){
        authorService.register(author);
        return Result.success();
    }

    @GetMapping("/get/list")
    public List<Author> getList(){
        return authorService.list();
    }

    @GetMapping("admin/get/{id}")
    public Author getById(@PathVariable Integer id){
        return authorService.getById(id);
    }

    @GetMapping("/get/info")
    public AuthorVo getInfo(){
        UserDetailsImpl currentPrincipal = (UserDetailsImpl) UserAuthenticationHelper.getCurrentPrincipal();
        Integer authorId = currentPrincipal.getAuthorId();
        return authorService.getAuthorInfo(authorId);
    }

}

