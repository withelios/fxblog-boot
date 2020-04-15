package com.fxtahe.fxblog.service.Impl;

import com.fxtahe.fxblog.entity.Category;
import com.fxtahe.fxblog.mapper.CategoryMapper;
import com.fxtahe.fxblog.service.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
