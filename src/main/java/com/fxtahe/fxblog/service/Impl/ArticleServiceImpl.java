package com.fxtahe.fxblog.service.Impl;

import com.fxtahe.fxblog.entity.Article;
import com.fxtahe.fxblog.entity.Relationship;
import com.fxtahe.fxblog.mapper.ArticleMapper;
import com.fxtahe.fxblog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxtahe.fxblog.service.RelationshipService;
import com.fxtahe.fxblog.vo.ArticleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private RelationshipService relationshipService;

    @Override
    public List<ArticleVo> findFeatureArticle() {
        return baseMapper.selectFeatureArticles();
    }

    @Override
    public void deleteArticle(Integer id) {
        removeById(id);
        relationshipService.deleteByCondition(new Relationship().setArticleId(id));
    }
}
