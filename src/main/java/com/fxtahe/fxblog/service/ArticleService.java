package com.fxtahe.fxblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxtahe.fxblog.entity.ArchiveArticle;
import com.fxtahe.fxblog.entity.Article;
import com.fxtahe.fxblog.vo.ArticleVo;
import com.fxtahe.fxblog.vo.PageRequest;
import com.fxtahe.fxblog.vo.PageResponse;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  文章服务接口
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
public interface ArticleService extends IService<Article> {

    /**
     * 新建文章
     * @param articleVo
     */
    void saveArticleVo(ArticleVo articleVo,Integer userId);

    /**
     * 获取文章
     * @param article
     * @return ArticleVo
     */
    ArticleVo getArticleVo(ArticleVo article);

    /**
     * 查询推荐文章
     * @return List<ArticleVo>
     */
    List<ArticleVo> getFeatureArticle(Integer userId);

    /**
     * 查询归档文章
     * @return List<ArticleVo>
     */
    Map<Integer,List<ArchiveArticle>> getArchiveArticle(Integer userId, Integer state);

    /**
     *
     * @param pageRequest 查询条件
     * @return IPage<ArticleVo>
     */
    PageResponse<ArticleVo> getArticleVoPage(PageRequest<ArticleVo> pageRequest);


    /**
     * 删除用户文章及关联数据
     * @param id 文章主键
     * @param userId 用户主键
     */
    void deleteArticle(Integer id,Integer userId);

    /**
     * 更新文章及关联数据
     * @param articleVo 文章数据
     */
    void updateArticleVo(ArticleVo articleVo,Integer userId);


    /**
     * 文章点赞
     * @param id
     */
    void likeArticle(Integer id);


    /**
     * 文章浏览量
     * @param id
     */
    void viewArticle(Integer id);



}
