package com.fxtahe.fxblog.service;

import com.fxtahe.fxblog.entity.Author;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxtahe.fxblog.vo.AuthorVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fxtahe
 * @since 2020-05-10
 */
public interface AuthorService extends IService<Author> {

    void register(Author author);

    AuthorVo getAuthorInfo(Integer id);
}
