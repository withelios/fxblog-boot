package com.fxtahe.fxblog.service.Impl;

import com.fxtahe.fxblog.entity.Article;
import com.fxtahe.fxblog.mapper.ArticleMapper;
import com.fxtahe.fxblog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
