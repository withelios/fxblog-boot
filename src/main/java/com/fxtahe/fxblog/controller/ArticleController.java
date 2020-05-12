package com.fxtahe.fxblog.controller;


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
 *  前端控制器
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

    @GetMapping("/get/feature")
    public List<ArticleVo> getFeatureArticle(){
        return articleService.getFeatureArticle();
    }

    @GetMapping("/get/{id}")
    public Article getArticle(@PathVariable Integer id){
        return articleService.getById(id);
    }

    @PostMapping("/get/page")
    public PageResponse<ArticleVo> getArticleVoPage(@RequestBody PageRequest<ArticleVo> pageRequest){
        return articleService.getArticleVoPage(pageRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Integer id){
        articleService.deleteArticle(id);
    }

    @PutMapping("/update")
    public void updateArticle(ArticleVo articleVo){
        articleService.updateArticleVo(articleVo);
    }


}

