package com.fxtahe.fxblog.controller;

import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.service.ArticleService;
import com.fxtahe.fxblog.service.AuthorService;
import com.fxtahe.fxblog.service.CategoryService;
import com.fxtahe.fxblog.service.TagService;
import com.fxtahe.fxblog.util.Const;
import com.fxtahe.fxblog.vo.ArticleVo;
import com.fxtahe.fxblog.vo.PageRequest;
import com.fxtahe.fxblog.vo.PageResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @description 页面展示控制器
* @author fxtahe
* @date 2020/6/16
*/
@RestController
@ResponseWrapper
@RequestMapping("/show")
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
        return articleService.getFeatureArticle();
    }

    @GetMapping("/article/get/{id}")
    public ArticleVo getArticleVo(@PathVariable Integer id){

        return null;
    }

    @GetMapping("/article/page")
    public PageResponse<ArticleVo> getArticleVoPage(@RequestBody PageRequest<ArticleVo> pageRequest){
        ArticleVo data = pageRequest.getData();
        data.setState(Const.ARTICLE_POSTED);
        pageRequest.setData(data);
        //return articleService.getArticleVoPage(pageRequest);
        return null;
    }


}
