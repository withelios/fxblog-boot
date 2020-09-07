package com.fxtahe.fxblog.controller;


import com.fxtahe.fxblog.config.annotation.OperationLog;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Author;
import com.fxtahe.fxblog.entity.Website;
import com.fxtahe.fxblog.security.UserAuthenticationHelper;
import com.fxtahe.fxblog.security.UserDetailsImpl;
import com.fxtahe.fxblog.service.AuthorService;
import com.fxtahe.fxblog.service.WebsiteService;
import com.fxtahe.fxblog.util.Const;
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
    private WebsiteService websiteService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public Result register(@Valid Author author){
        authorService.register(author);
        return Result.success();
    }

    /**
     * 管理员查看用户列表
     * @return
     */
    @OperationLog(operationModule = Const.AUTHOR_TYPE,operationType = Const.OPERA_TYPE_SELECT,operationDesc = "用户分页")
    @GetMapping("/admin/get/list")
    public List<Author> getList(){
        return authorService.list();
    }

    /**
     * 管理员查看用户信息
     * @param id
     * @return
     */
    @OperationLog(operationModule = Const.AUTHOR_TYPE,operationType = Const.OPERA_TYPE_SELECT,operationDesc = "查看用户信息")
    @GetMapping("/admin/get/{id}")
    public Author getById(@PathVariable Integer id){
        return authorService.getById(id);
    }

    /**
     * 登陆获取信息
     * @return
     */
    @OperationLog(operationModule = Const.AUTHOR_TYPE,operationType = "登录",operationDesc = "用户登录")
    @GetMapping("/get/info")
    public AuthorVo getInfo(){
        UserDetailsImpl currentPrincipal = (UserDetailsImpl) UserAuthenticationHelper.getCurrentPrincipal();
        Integer authorId = currentPrincipal.getAuthorId();
        return authorService.getAuthorInfo(authorId);
    }

    /**
     * 更新作者信息
     * @param author
     * @return
     */
    @OperationLog(operationModule = Const.AUTHOR_TYPE,operationType = Const.OPERA_TYPE_UPDATE,operationDesc = "更新用户信息")
    @PutMapping("/update/info")
    public Result updateInfo(@RequestBody Author author){
        UserDetailsImpl currentPrincipal = (UserDetailsImpl) UserAuthenticationHelper.getCurrentPrincipal();
        Integer id = currentPrincipal.getAuthorId();
        authorService.updateById(author.setId(id));
        return Result.success();
    }

    /**
     * 更新头像
     * @param author
     * @return
     */
    @OperationLog(operationModule = Const.AUTHOR_TYPE,operationType = Const.OPERA_TYPE_UPDATE,operationDesc = "更新头像")
    @PutMapping("/update/avatar")
    public Result updateAvatar(@RequestBody Author author){
        UserDetailsImpl currentPrincipal = (UserDetailsImpl) UserAuthenticationHelper.getCurrentPrincipal();
        Integer id = currentPrincipal.getAuthorId();
        authorService.updateById(author.setId(id));
        return Result.success();
    }

    /**
     * 更新密码
     * @param formData
     * @return
     */
    @OperationLog(operationModule = Const.AUTHOR_TYPE,operationType = Const.OPERA_TYPE_UPDATE,operationDesc = "更新密码")
    @PutMapping("/update/password")
    public Result updatePassword(@RequestBody Map<String,String> formData){
        UserDetailsImpl currentPrincipal = (UserDetailsImpl) UserAuthenticationHelper.getCurrentPrincipal();
        Integer authorId = currentPrincipal.getAuthorId();
        authorService.updateById(new Author().setId(authorId).setPassword(bCryptPasswordEncoder.encode(formData.get("password"))));
        return Result.success();
    }





}

