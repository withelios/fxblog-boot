package com.fxtahe.fxblog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxtahe.fxblog.entity.Article;
import com.fxtahe.fxblog.entity.Category;
import com.fxtahe.fxblog.entity.Relationship;
import com.fxtahe.fxblog.entity.Tag;
import com.fxtahe.fxblog.mapper.ArticleMapper;
import com.fxtahe.fxblog.service.ArticleService;
import com.fxtahe.fxblog.service.RelationshipService;
import com.fxtahe.fxblog.util.Const;
import com.fxtahe.fxblog.vo.ArticleVo;
import com.fxtahe.fxblog.vo.PageRequest;
import com.fxtahe.fxblog.vo.PageResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private RelationshipService relationshipService;

    @Override
    public List<ArticleVo> getFeatureArticle() {
        return baseMapper.selectFeatureArticles();
    }

    @Override
    public PageResponse<ArticleVo> getArticleVoPage(PageRequest<ArticleVo> pageRequest) {

        Long current = pageRequest.getCurrent();
        Long size = pageRequest.getSize();
        ArticleVo data = pageRequest.getData();
        if(current <=0){
            current = 1L;
        }
        pageRequest.setLimit((current-1)*size);
        List<ArticleVo> result =  baseMapper.selectArticleVoPage(pageRequest);

        Long total = baseMapper.selectCountArticleVoPage(pageRequest);
        return new PageResponse<ArticleVo>().setCurrent(current).setSize(size).setData(result).setTotal(total);



    }

    @Override
    public void deleteArticle(Integer id) {
        removeById(id);
        relationshipService.deleteByCondition(new Relationship().setArticleId(id));
    }

    @Override
    public void updateArticleVo(ArticleVo articleVo) {
        Integer id = articleVo.getId();
        articleVo.insertOrUpdate();

        relationshipService.deleteByCondition(new Relationship().setArticleId(id));
        List<Tag> tags = articleVo.getTags();
        Relationship relationship = new Relationship();
        if (!CollectionUtils.isEmpty(tags)) {
            for (Tag tag : tags) {
                Integer tId = tag.getId();
                if (tId != null) {
                    relationship.setRId(tId).setArticleId(id)
                            .setRType(Const.TAG_TYPE).insert();
                } else {
                    tag.insert();
                    relationship.setRId(tag.getId()).insert();
                }
            }
        }
        Category category = articleVo.getCategory();
        if(category != null){
            if(category.getId() == null){
                category.insert();
            }
            relationship.setRId(category.getId()).setArticleId(id).setRType(Const.CATEGORY_TYPE);
            relationship.insert();
        }
    }


}
