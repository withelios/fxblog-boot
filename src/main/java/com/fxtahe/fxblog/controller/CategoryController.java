package com.fxtahe.fxblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxtahe.fxblog.config.annotation.AuthorParameter;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Category;
import com.fxtahe.fxblog.security.UserAuthenticationHelper;
import com.fxtahe.fxblog.security.UserDetailsImpl;
import com.fxtahe.fxblog.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  Category前端控制器
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
@ResponseWrapper
@RestController
@RequestMapping("/author/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/save")
    public void saveCategory(@RequestBody Category category){
        UserDetailsImpl user = (UserDetailsImpl) UserAuthenticationHelper.getCurrentPrincipal();
        category.setAuthorId(user.getAuthorId());
        categoryService.save(category);
    }

    @GetMapping("/page/{current}/{size}")
    public Page<Category> getPage(@PathVariable Long current, @PathVariable Long size, @AuthorParameter Integer userId){
        return categoryService.page(new Page<Category>().setCurrent(current).setSize(size)
                ,new QueryWrapper<Category>().eq(userId!=null,"author_id",userId));
    }

    @GetMapping("/list")
    public List<Category> getList(@AuthorParameter Integer userId){
        return categoryService.list(new QueryWrapper<Category>().eq(userId!=null,"author_id",userId).orderByAsc("id"));
    }

    @GetMapping("/get/{id}")
    public Category getById(@PathVariable Integer id,@AuthorParameter Integer userId){
        return categoryService.getOne(new QueryWrapper<Category>().eq(userId!=null,"author_id",userId).eq("id",id));
    }

    @GetMapping("/search/{value}")
    public List<Category> searchTag(@PathVariable String value,@AuthorParameter Integer userId){
        return categoryService.list(new QueryWrapper<Category>()
                .eq(userId!=null,"author_id",userId)
                .like("category_name",value)
                .or().like("description",value).orderByAsc("id"));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id,@AuthorParameter Integer userId){
        categoryService.deleteCategory(id,userId);
    }

    @PutMapping("/update")
    public void updateCategory(@RequestBody Category category,@AuthorParameter Integer userId){
        categoryService.update(category,new UpdateWrapper<Category>().eq(userId!=null,"author_id",userId).eq("id",category.getId()));
    }
}

