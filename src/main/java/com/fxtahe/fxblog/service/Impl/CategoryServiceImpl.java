package com.fxtahe.fxblog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxtahe.fxblog.entity.Category;
import com.fxtahe.fxblog.entity.Relationship;
import com.fxtahe.fxblog.mapper.CategoryMapper;
import com.fxtahe.fxblog.service.CategoryService;
import com.fxtahe.fxblog.service.RelationshipService;
import com.fxtahe.fxblog.util.Const;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private RelationshipService relationshipService;

    @Override
    public void deleteCategory(Integer id) {
        removeById(id);
        relationshipService.deleteByCondition(new Relationship().setRId(id).setRType(Const.CATEGORY_TYPE));
    }
}
