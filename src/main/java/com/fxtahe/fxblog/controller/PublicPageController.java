package com.fxtahe.fxblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Article;
import com.fxtahe.fxblog.entity.Category;
import com.fxtahe.fxblog.entity.Tag;
import com.fxtahe.fxblog.service.ArticleService;
import com.fxtahe.fxblog.service.AuthorService;
import com.fxtahe.fxblog.service.CategoryService;
import com.fxtahe.fxblog.service.TagService;
import com.fxtahe.fxblog.util.Const;
import com.fxtahe.fxblog.vo.ArticleVo;
import com.fxtahe.fxblog.vo.AuthorVo;
import com.fxtahe.fxblog.vo.PageRequest;
import com.fxtahe.fxblog.vo.PageResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

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

    @GetMapping("/article/feature")
    public List<ArticleVo> getFeatureArticle(){
        return articleService.getFeatureArticle(null);
    }

    @GetMapping("/article/archive/current")
    public List<ArticleVo> getArchiveArticle(){
        return articleService.getArchiveArticle(null);
    }
    @PutMapping("/article/like/{id}")
    public void likeArticle(@PathVariable Integer id){
        articleService.update(new UpdateWrapper<Article>().set("like","like+1").eq("id",id).eq("state",Const.ARTICLE_POSTED));
    }
    @GetMapping("/article/get/{id}")
    public ArticleVo getArticleVo(@PathVariable Integer id){
        Article article = new Article();
        article.setId(id).setState(Const.ARTICLE_POSTED);
        return articleService.getArticleVo(article);
    }

    @GetMapping("/article/page")
    public PageResponse<ArticleVo> getArticleVoPage(@RequestParam(value = "categoryId",required = false)Integer categoryId,
                                                    @RequestParam(value="tagId",required = false)Integer tagId,
                                                    @RequestParam(value = "authorId",required = false)Integer authorId,
                                                    @RequestParam(value = "page",required = false)Long page){
        PageRequest<ArticleVo> pageRequest = new PageRequest<>();
        pageRequest.setCurrent(page);
        ArticleVo data = new ArticleVo();
        data.setAuthorId(authorId);
        data.setCategory(new Category().setId(categoryId));
        data.setTags(Collections.singletonList(new Tag().setId(tagId)));
        data.setState(Const.ARTICLE_POSTED);
        pageRequest.setData(data);
        return articleService.getArticleVoPage(pageRequest,null);
    }

    @GetMapping("/tag/tags")
    public List<Tag> getTags(){
        return tagService.list(new QueryWrapper<Tag>().select("DISTINCT(tag_name)"));
    }

    @GetMapping("/category/categories")
    public List<Category> getCategories(){
        return categoryService.list(new QueryWrapper<Category>().select("DISTINCT(category_name)"));
    }

    @GetMapping("/authorInfo/list")
    public List<AuthorVo> getAuthorVOs(){
        return authorService.getAuthorVOS();
    }

    @GetMapping("/article/get/tag/{name}")
    public PageResponse<ArticleVo> getArticlesByTagName(@PathVariable String name){


        return null;
    }
}
