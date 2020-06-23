package com.fxtahe.fxblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fxtahe.fxblog.config.annotation.AuthorParameter;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Article;
import com.fxtahe.fxblog.security.UserAuthenticationHelper;
import com.fxtahe.fxblog.security.UserDetailsImpl;
import com.fxtahe.fxblog.service.ArticleService;
import com.fxtahe.fxblog.vo.ArticleVo;
import com.fxtahe.fxblog.vo.PageRequest;
import com.fxtahe.fxblog.vo.PageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  Admin Article前端控制器
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
@Slf4j
@RestController
@ResponseWrapper
@RequestMapping("/author/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 获取推荐文章列表
     * @return List<ArticleVo>
     */
    @GetMapping("/feature")
    public List<ArticleVo> getFeatureArticle(){
        return articleService.getFeatureArticle();
    }

    /**
     * 获取指定ID 文章
     * @param id
     * @return Article
     */
    @GetMapping("/get/{id}")
    public Article getArticle(@PathVariable Integer id, @AuthorParameter Integer userId){
        return articleService.getOne(new QueryWrapper<Article>().eq("id",id).eq(userId!=null,"author_id",userId));
    }

    @PostMapping("/page")
    public PageResponse<ArticleVo> getArticleVoPage(@RequestBody PageRequest<ArticleVo> pageRequest,@AuthorParameter Integer userId){
        return articleService.getArticleVoPage(pageRequest,userId);
    }

    @PostMapping("/save")
    public void saveArticleVo(@RequestBody ArticleVo articleVo){
        UserDetailsImpl userDetails = (UserDetailsImpl) UserAuthenticationHelper.getCurrentPrincipal();
        Integer authorId = userDetails.getAuthorId();
        articleService.saveArticleVo(articleVo,authorId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Integer id,@AuthorParameter Integer userId){
        articleService.deleteArticle(id,userId);
    }

    @PutMapping("/update")
    public void updateArticle(@RequestBody ArticleVo articleVo,@AuthorParameter Integer userId){
        articleService.updateArticleVo(articleVo,userId);
    }

    @PutMapping("/feature")
    public void updateFeature(@RequestBody Article article,@AuthorParameter Integer userId){
        articleService.update(new UpdateWrapper<Article>().eq(userId!=null,"author_id",userId)
                .eq("id",article.getId()).set("feature",article.getFeature()));
    }


}

