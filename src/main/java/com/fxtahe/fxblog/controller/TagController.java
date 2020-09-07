package com.fxtahe.fxblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxtahe.fxblog.config.annotation.AuthorParameter;
import com.fxtahe.fxblog.config.annotation.OperationLog;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Tag;
import com.fxtahe.fxblog.service.TagService;
import com.fxtahe.fxblog.util.Const;
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

    /**
     * 分页标签
     * @param current
     * @param size
     * @param userId
     * @return
     */
    @OperationLog(operationModule = Const.TAG_TYPE,operationType = Const.OPERA_TYPE_SELECT,operationDesc = "分页标签")
    @GetMapping("/page/{current}/{size}")
    public Page<Tag> getPage(@PathVariable Long current, @PathVariable Long size, @AuthorParameter Integer userId){
        return tagService.page(
                new Page<Tag>().setCurrent(current).setSize(size)
                ,new QueryWrapper<Tag>().eq(userId!=null,"author_id",userId));
    }

    /**
     * 获取所有标签
     * @param userId
     * @return
     */
    @OperationLog(operationModule = Const.TAG_TYPE,operationType = Const.OPERA_TYPE_SELECT,operationDesc = "标签列表")
    @GetMapping("/list")
    public List<Tag> getList(@AuthorParameter Integer userId){
        return tagService.list(new QueryWrapper<Tag>()
                .eq(userId!=null,"author_id",userId)
                .orderByAsc("id"));
    }

    /**
     * 根据ID查看标签
     * @param id
     * @param userId
     * @return
     */
    @OperationLog(operationModule = Const.TAG_TYPE,operationType = Const.OPERA_TYPE_SELECT,operationDesc = "查看指定标签")
    @GetMapping("/get/{id}")
    public Tag getById(@PathVariable Integer id,@AuthorParameter Integer userId){
        return tagService.getOne(new QueryWrapper<Tag>().eq(userId!=null,"author_id",userId).eq("id",id));
    }

    /**
     * 搜索标签
     * @param name
     * @param userId
     * @return
     */
    @OperationLog(operationModule = Const.TAG_TYPE,operationType = Const.OPERA_TYPE_SELECT,operationDesc = "搜索标签")
    @GetMapping("/search/{name}")
    public List<Tag> searchTag(@PathVariable String name,@AuthorParameter Integer userId){
        return tagService.list(new QueryWrapper<Tag>()
                .eq(userId!=null,"author_id",userId)
                .like("tag_name",name).orderByAsc("id"));
    }

    /**
     * 删除标签
     * @param id
     * @param userId
     */
    @OperationLog(operationModule = Const.TAG_TYPE,operationType = Const.OPERA_TYPE_DEL,operationDesc = "删除标签")
    @DeleteMapping("/delete/{id}")
    public void deleteTag(@PathVariable Integer id,@AuthorParameter Integer userId){
        tagService.deleteTag(id,userId);
    }

    /**
     * 更新标签
     * @param tag
     * @param userId
     */
    @OperationLog(operationModule = Const.TAG_TYPE,operationType = Const.OPERA_TYPE_UPDATE,operationDesc = "更新标签")
    @PutMapping("/update")
    public void updateCategory(@RequestBody Tag tag,@AuthorParameter Integer userId){
        tagService.update(tag,new QueryWrapper<Tag>()
                .eq(userId!=null,"author_id",userId)
                .eq("id",tag.getId()));
    }
}

