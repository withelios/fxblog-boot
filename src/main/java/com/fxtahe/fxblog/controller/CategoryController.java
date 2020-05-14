package com.fxtahe.fxblog.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Category;
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
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/save")
    public void saveCategory(Category category){
        categoryService.save(category);
    }

    @GetMapping("/page/{current}/{size}")
    public Page<Category> getPage(@PathVariable Long current,@PathVariable Long size){
        return categoryService.page(new Page<Category>().setCurrent(current).setSize(size));
    }
    @GetMapping("/list")
    public List<Category> getList(){
        return categoryService.list();
    }

    @GetMapping("/get/{id}")
    public Category getById(@PathVariable Integer id){
        return categoryService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id){
        categoryService.deleteCategory(id);
    }

    @PutMapping("/update")
    public void updateCategory(@RequestBody Category category){
        categoryService.updateById(category);
    }
}

