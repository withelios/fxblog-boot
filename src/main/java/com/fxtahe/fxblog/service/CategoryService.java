package com.fxtahe.fxblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxtahe.fxblog.entity.Category;
import com.fxtahe.fxblog.vo.CategoryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
public interface CategoryService extends IService<Category> {

    void deleteCategory(Integer id,Integer userId);

    List<CategoryVo> searchArticles(String key);
}
