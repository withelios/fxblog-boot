package com.fxtahe.fxblog.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxtahe.fxblog.entity.Category;
import com.fxtahe.fxblog.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/save")
    public void saveCategory(Category category){
        categoryService.save(category);
    }

    @GetMapping("/get/page/{current}")
    public Page<Category> getPage(@PathVariable Long current){
        return categoryService.page(new Page<Category>().setCurrent(current));
    }

    @GetMapping("/get/list")
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
    public void updateCategory(Category category){
        categoryService.updateById(category);
    }

}

