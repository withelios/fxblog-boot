package com.fxtahe.fxblog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxtahe.fxblog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<Article> selectPageVo(Page<?> page, Integer state);
}
