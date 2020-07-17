package com.fxtahe.fxblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxtahe.fxblog.entity.Author;
import com.fxtahe.fxblog.vo.AuthorVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fxtahe
 * @since 2020-05-10
 */
public interface AuthorMapper extends BaseMapper<Author> {

    List<AuthorVo> selectAuthorVos();
}
