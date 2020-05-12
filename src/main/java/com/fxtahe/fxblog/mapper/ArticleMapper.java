package com.fxtahe.fxblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxtahe.fxblog.entity.Article;
import com.fxtahe.fxblog.entity.Tag;
import com.fxtahe.fxblog.vo.ArticleVo;
import com.fxtahe.fxblog.vo.PageRequest;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
public interface ArticleMapper extends BaseMapper<Article> {

    //IPage<Article> selectPageVo(Page<?> page, Integer state);
    List<ArticleVo> selectFeatureArticles();

    List<ArticleVo> selectArticleVoPage(PageRequest<ArticleVo> articleVo);

    Long selectCountArticleVoPage(PageRequest<ArticleVo>  articleVo);

    List<Tag> selectRelationTags(Integer id);
}
