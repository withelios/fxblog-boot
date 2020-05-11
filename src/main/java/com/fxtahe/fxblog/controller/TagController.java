package com.fxtahe.fxblog.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxtahe.fxblog.entity.Tag;
import com.fxtahe.fxblog.service.TagService;
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
@RequestMapping("/tag")
public class TagController {

    @Resource
    private TagService tagService;

    @PostMapping("/save")
    public void saveTag(Tag tag){
        tagService.save(tag);
    }

    @GetMapping("/get/page/{current}")
    public Page<Tag> getPage(@PathVariable Long current){
        return tagService.page(new Page<Tag>().setCurrent(current));
    }

    @GetMapping("/get/list")
    public List<Tag> getList(){
        return tagService.list();
    }

    @GetMapping("/get/{id}")
    public Tag getById(@PathVariable Integer id){
        return tagService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTag(@PathVariable Integer id){
        tagService.deleteTag(id);
    }

    @PutMapping("/update")
    public void updateTag(Tag tag){
        tagService.updateById(tag);
    }
}

