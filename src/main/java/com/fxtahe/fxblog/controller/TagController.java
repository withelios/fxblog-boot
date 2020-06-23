package com.fxtahe.fxblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxtahe.fxblog.config.annotation.AuthorParameter;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Tag;
import com.fxtahe.fxblog.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  Tag前端控制器
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
@ResponseWrapper
@RestController
@RequestMapping("/author/tag")
public class TagController {

    @Resource
    private TagService tagService;

    @PostMapping("/save")
    public void saveTag(Tag tag){
        tagService.saveOrUpdate(tag);
    }

    @GetMapping("/page/{current}/{size}")
    public Page<Tag> getPage(@PathVariable Long current, @PathVariable Long size, @AuthorParameter Integer userId){
        return tagService.page(
                new Page<Tag>().setCurrent(current).setSize(size)
                ,new QueryWrapper<Tag>().eq(userId!=null,"author_id",userId));
    }

    @GetMapping("/list")
    public List<Tag> getList(@AuthorParameter Integer userId){
        return tagService.list(new QueryWrapper<Tag>()
                .eq(userId!=null,"author_id",userId)
                .orderByAsc("id"));
    }

    @GetMapping("/get/{id}")
    public Tag getById(@PathVariable Integer id,@AuthorParameter Integer userId){
        return tagService.getOne(new QueryWrapper<Tag>().eq(userId!=null,"author_id",userId).eq("id",id));
    }

    @GetMapping("/search/{name}")
    public List<Tag> searchTag(@PathVariable String name,@AuthorParameter Integer userId){
        return tagService.list(new QueryWrapper<Tag>()
                .eq(userId!=null,"author_id",userId)
                .like("tag_name",name).orderByAsc("id"));
    }
    @DeleteMapping("/delete/{id}")
    public void deleteTag(@PathVariable Integer id,@AuthorParameter Integer userId){
        tagService.deleteTag(id,userId);
    }

    @PutMapping("/update")
    public void updateCategory(@RequestBody Tag tag,@AuthorParameter Integer userId){
        tagService.update(tag,new QueryWrapper<Tag>()
                .eq(userId!=null,"author_id",userId)
                .eq("id",tag.getId()));
    }
}

