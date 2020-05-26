package com.fxtahe.fxblog.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Article;
import com.fxtahe.fxblog.service.ArticleService;
import com.fxtahe.fxblog.vo.ArticleVo;
import com.fxtahe.fxblog.vo.PageRequest;
import com.fxtahe.fxblog.vo.PageResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  Article前端控制器
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
@RestController
@ResponseWrapper
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/feature")
    public List<ArticleVo> getFeatureArticle(){
        return articleService.getFeatureArticle();
    }

    @GetMapping("/get/{id}")
    public Article getArticle(@PathVariable Integer id){
        return articleService.getById(id);
    }

    @PostMapping("/page")
    public PageResponse<ArticleVo> getArticleVoPage(@RequestBody PageRequest<ArticleVo> pageRequest){
        return articleService.getArticleVoPage(pageRequest);
    }

    @PostMapping("/save")
    public void saveArticleVo(@RequestBody ArticleVo articleVo){
        articleService.saveArticleVo(articleVo);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Integer id){
        articleService.deleteArticle(id);
    }

    @PutMapping("/update")
    public void updateArticle(@RequestBody ArticleVo articleVo){
        articleService.updateArticleVo(articleVo);
    }

    @PutMapping("/feature")
    public void updateFeature(@RequestBody Article article){
        articleService.update(new UpdateWrapper<Article>().eq("id",article.getId()).set("feature",article.getFeature()));
    }


}

