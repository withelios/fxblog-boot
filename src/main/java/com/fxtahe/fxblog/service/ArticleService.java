package com.fxtahe.fxblog.service;

import com.fxtahe.fxblog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxtahe.fxblog.vo.ArticleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
public interface ArticleService extends IService<Article> {

    List<ArticleVo> findFeatureArticle();
}
