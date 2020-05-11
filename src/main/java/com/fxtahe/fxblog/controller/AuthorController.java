package com.fxtahe.fxblog.controller;


import com.fxtahe.fxblog.entity.Author;
import com.fxtahe.fxblog.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fxtahe
 * @since 2020-05-10
 */
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Resource
    private AuthorService authorService;

    @GetMapping("/get/list")
    public List<Author> getList(){
        return authorService.list();
    }

    @GetMapping("/get/{id}")
    public Author getById(@PathVariable Integer id){
        return authorService.getById(id);
    }
}

