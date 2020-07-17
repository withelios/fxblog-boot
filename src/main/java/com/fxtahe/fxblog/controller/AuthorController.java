package com.fxtahe.fxblog.controller;


import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Author;
import com.fxtahe.fxblog.security.UserAuthenticationHelper;
import com.fxtahe.fxblog.security.UserDetailsImpl;
import com.fxtahe.fxblog.service.AuthorService;
import com.fxtahe.fxblog.vo.AuthorVo;
import com.fxtahe.fxblog.vo.wrapper.Result;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public Result register(@Valid Author author){
        authorService.register(author);
        return Result.success();
    }

    @GetMapping("/get/list")
    public List<Author> getList(){
        return authorService.list();
    }

    @GetMapping("/admin/get/{id}")
    public Author getById(@PathVariable Integer id){
        return authorService.getById(id);
    }

    @GetMapping("/get/info")
    public AuthorVo getInfo(){
        UserDetailsImpl currentPrincipal = (UserDetailsImpl) UserAuthenticationHelper.getCurrentPrincipal();
        Integer authorId = currentPrincipal.getAuthorId();
        return authorService.getAuthorInfo(authorId);
    }

    @PutMapping("/update/info")
    public Result updateInfo(@RequestBody Author author){
        UserDetailsImpl currentPrincipal = (UserDetailsImpl) UserAuthenticationHelper.getCurrentPrincipal();
        Integer id = currentPrincipal.getAuthorId();
        authorService.updateById(author.setId(id));
        return Result.success();
    }

    @PutMapping("/update/avatar")
    public Result updateAvatar(@RequestBody Author author){
        UserDetailsImpl currentPrincipal = (UserDetailsImpl) UserAuthenticationHelper.getCurrentPrincipal();
        Integer id = currentPrincipal.getAuthorId();
        authorService.updateById(author.setId(id));
        return Result.success();
    }

    @PutMapping("/update/password")
    public Result updatePassword(@RequestBody Map<String,String> formData){
        UserDetailsImpl currentPrincipal = (UserDetailsImpl) UserAuthenticationHelper.getCurrentPrincipal();
        Integer authorId = currentPrincipal.getAuthorId();
        authorService.updateById(new Author().setId(authorId).setPassword(bCryptPasswordEncoder.encode(formData.get("password"))));
        return Result.success();
    }

    @DeleteMapping("/admin/delete/{id}")
    public Result deleteAuthor(@PathVariable Integer id){
        authorService.removeById(id);
        return Result.success();
    }
}

