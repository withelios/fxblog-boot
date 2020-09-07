package com.fxtahe.fxblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fxtahe.fxblog.config.annotation.AuthorParameter;
import com.fxtahe.fxblog.config.annotation.OperationLog;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Article;
import com.fxtahe.fxblog.security.UserAuthenticationHelper;
import com.fxtahe.fxblog.security.UserDetailsImpl;
import com.fxtahe.fxblog.service.ArticleService;
import com.fxtahe.fxblog.util.Const;
import com.fxtahe.fxblog.vo.ArticleVo;
import com.fxtahe.fxblog.vo.PageRequest;
import com.fxtahe.fxblog.vo.PageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
    @OperationLog(operationModule = Const.ARTICLE_TYPE,operationType = "查看推荐",operationDesc = "查看推荐文章")
    @GetMapping("/feature")
    public List<ArticleVo> getFeatureArticle(@AuthorParameter Integer userId){
        return articleService.getFeatureArticle(userId);
    }

    /**
     * 获取指定ID 文章
     * @param id
     * @return Article
     */
    @OperationLog(operationModule = Const.ARTICLE_TYPE,operationType = Const.OPERA_TYPE_SELECT,operationDesc = "查看指定文章")
    @GetMapping("/get/{id}")
    public Article getArticle(@PathVariable Integer id, @AuthorParameter Integer userId){
        return articleService.getOne(new QueryWrapper<Article>().eq("id",id).eq(userId!=null,"author_id",userId));
    }

    /**
     * 分页文章
     * @param pageRequest
     * @param userId
     * @return
     */
    @OperationLog(operationModule = Const.ARTICLE_TYPE,operationType = Const.OPERA_TYPE_SELECT,operationDesc = "分页查看文章")
    @PostMapping("/page")
    public PageResponse<ArticleVo> getArticleVoPage(@RequestBody PageRequest<ArticleVo> pageRequest,@AuthorParameter Integer userId){
        pageRequest.setData((ArticleVo) pageRequest.getData().setAuthorId(userId));
        return articleService.getArticleVoPage(pageRequest);
    }

    /**
     * 保存文章
     * @param articleVo
     */
    @OperationLog(operationModule = Const.ARTICLE_TYPE,operationType = Const.OPERA_TYPE_ADD,operationDesc = "新增文章")
    @PostMapping("/save")
    public void saveArticleVo(@RequestBody ArticleVo articleVo){
        UserDetailsImpl userDetails = (UserDetailsImpl) UserAuthenticationHelper.getCurrentPrincipal();
        Integer authorId = userDetails.getAuthorId();
        articleService.saveArticleVo(articleVo,authorId);
    }

    /**
     * 删除文章
     * @param id
     * @param userId
     */
    @OperationLog(operationModule = Const.ARTICLE_TYPE,operationType = Const.OPERA_TYPE_DEL,operationDesc = "删除指定文章")
    @CacheEvict(value = "articles",key = "#id")
    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Integer id,@AuthorParameter Integer userId){
        articleService.deleteArticle(id,userId);
    }

    /**
     * 更新文章
     * @param articleVo
     * @param userId
     */
    @OperationLog(operationModule = Const.ARTICLE_TYPE,operationType = Const.OPERA_TYPE_UPDATE,operationDesc = "更新指定文章")
    @CachePut(value="articles",key="#articleVo.id")
    @PutMapping("/update")
    public void updateArticle(@RequestBody ArticleVo articleVo,@AuthorParameter Integer userId){
        articleService.updateArticleVo(articleVo,userId);
    }

    /**
     * 设置推荐文章
     * @param article
     * @param userId
     */
    @OperationLog(operationModule = Const.ARTICLE_TYPE,operationType = "设置推荐",operationDesc = "设置推荐文章")
    @CacheEvict(value = "articles",key = "#article.id")
    @PutMapping("/feature")
    public void updateFeature(@RequestBody Article article,@AuthorParameter Integer userId){
        articleService.update(new UpdateWrapper<Article>().eq(userId!=null,"author_id",userId)
                .eq("id",article.getId()).set("feature",article.getFeature()));
    }


}

