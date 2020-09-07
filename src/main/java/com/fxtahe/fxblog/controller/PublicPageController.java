package com.fxtahe.fxblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.ArchiveArticle;
import com.fxtahe.fxblog.entity.Category;
import com.fxtahe.fxblog.entity.Tag;
import com.fxtahe.fxblog.service.ArticleService;
import com.fxtahe.fxblog.service.AuthorService;
import com.fxtahe.fxblog.service.CategoryService;
import com.fxtahe.fxblog.service.TagService;
import com.fxtahe.fxblog.util.Const;
import com.fxtahe.fxblog.vo.*;
import com.fxtahe.fxblog.vo.wrapper.Result;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
* @description 页面展示控制器
* @author fxtahe
* @date 2020/6/16
*/
@RestController
@ResponseWrapper
@RequestMapping("/fxblog")
public class PublicPageController {

    @Resource
    private ArticleService articleService;

    @Resource
    private TagService tagService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private AuthorService authorService;


    /**
     * 获取推荐文章
     * @return
     */
    @GetMapping("/article/feature")
    public List<ArticleVo> getFeatureArticle(){
        return articleService.getFeatureArticle(null);
    }

    /**
     * 获取归档文章
     * @return
     */
    @GetMapping("/article/archive")
    public Map<Integer,List<ArchiveArticle>> getArchiveArticle(){
        return articleService.getArchiveArticle(null,Const.ARTICLE_POSTED);
    }

    /**
     * 点赞文章
     * @param id
     */
    @CacheEvict(value="article",key="#id")
    @PutMapping("/article/like/{id}")
    public Result likeArticle(@PathVariable Integer id){
        articleService.likeArticle(id);
        return Result.success();
    }

    /**
     * 获取文章
     * @param id
     * @return
     */
    @GetMapping("/article/get/{id}")
    public ArticleVo getArticleVo(@PathVariable Integer id){
        ArticleVo article = new ArticleVo();
        article.setId(id).setState(Const.ARTICLE_POSTED);
        articleService.viewArticle(id);
        return articleService.getArticleVo(article);
    }

    /**
     * 分页查询文章
     * @param categoryId
     * @param tagId
     * @param authorId
     * @param page
     * @return
     */
    @GetMapping("/article/page")
    public PageResponse<ArticleVo> getArticleVoPage(@RequestParam(value = "categoryId",required = false)Integer categoryId,
                                                    @RequestParam(value="tagId",required = false)Integer tagId,
                                                    @RequestParam(value = "authorId",required = false)Integer authorId,
                                                    @RequestParam(value = "page",required = false)Long page){
        PageRequest<ArticleVo> pageRequest = new PageRequest<>();
        pageRequest.setCurrent(page);
        ArticleVo data = new ArticleVo();
        data.setAuthorId(authorId);
        if(categoryId!=null)data.setCategory(new Category().setId(categoryId));
        if (tagId != null) data.setTags(Collections.singletonList(new Tag().setId(tagId)));
        data.setState(Const.ARTICLE_POSTED);
        pageRequest.setData(data);
        return articleService.getArticleVoPage(pageRequest);
    }

    /**
     * 获取标签
     * @return
     */
    @GetMapping("/tag/tags")
    public List<Tag> getTags(){
        return tagService.list(new QueryWrapper<Tag>().select("DISTINCT(tag_name)"));
    }

    /**
     * 获取分类
     * @return
     */
    @GetMapping("/category/categories")
    public List<Category> getCategories(){
        return categoryService.list(new QueryWrapper<Category>().select("DISTINCT(category_name)"));
    }

    /**
     * 获取作者信息
     * @return
     */
    @GetMapping("/authorInfo/list")
    public List<AuthorVo> getAuthorVOs(){
        return authorService.getAuthorVOS();
    }

    @Cacheable(value = "article",key = "#key")
    @GetMapping("/article/search/{key}")
    public List<CategoryVo> searchArticles(@PathVariable String key){

       return StringUtils.isEmpty(key.trim()) ? new ArrayList<>() :categoryService.searchArticles(key);
        //return StringUtils.isEmpty(key.trim()) ? new ArrayList<>() :articleService.list(new QueryWrapper<Article>().select("id","title","create_date").eq("state",Const.ARTICLE_POSTED).like("title",key));
    }
}
