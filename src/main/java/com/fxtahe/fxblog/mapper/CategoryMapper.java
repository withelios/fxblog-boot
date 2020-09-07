package com.fxtahe.fxblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxtahe.fxblog.entity.Category;
import com.fxtahe.fxblog.vo.CategoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVo> searchArticles(@Param("key") String key);

}
