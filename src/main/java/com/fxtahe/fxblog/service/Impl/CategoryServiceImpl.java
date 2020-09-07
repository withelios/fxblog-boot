package com.fxtahe.fxblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxtahe.fxblog.entity.Category;
import com.fxtahe.fxblog.entity.Relationship;
import com.fxtahe.fxblog.mapper.CategoryMapper;
import com.fxtahe.fxblog.service.CategoryService;
import com.fxtahe.fxblog.service.RelationshipService;
import com.fxtahe.fxblog.util.Const;
import com.fxtahe.fxblog.vo.CategoryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = Exception.class)
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private RelationshipService relationshipService;

    @Override
    public void deleteCategory(Integer id,Integer userId) {
        baseMapper.delete(new QueryWrapper<Category>().eq(userId!=null,"author_id",userId).eq("id",id));
        relationshipService.deleteByCondition(new Relationship().setRId(id).setRType(Const.CATEGORY_TYPE));
    }

    @Override
    public List<CategoryVo> searchArticles(String key) {

        return baseMapper.searchArticles(key);
    }
}
