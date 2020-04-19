package com.fxtahe.fxblog.controller;


import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Article;
import com.fxtahe.fxblog.service.ArticleService;
import com.fxtahe.fxblog.vo.ArticleVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/feature")
    public List<ArticleVo> getFeatureArticle(){

        return articleService.findFeatureArticle();
    }

    @GetMapping("/homePage")
    public List<Article> getArticlePageVo(){

        return articleService.list();
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable Integer id){

        return articleService.getById(id);
    }

}

