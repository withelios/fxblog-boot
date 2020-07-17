package com.fxtahe.fxblog.vo;

import com.fxtahe.fxblog.entity.Author;
import com.fxtahe.fxblog.entity.Category;
import com.fxtahe.fxblog.entity.Tag;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *     AuthorVo
 * </p>
 * @author fxtahe
 * @since 2020/7/15
 */
@Data
public class AuthorVo extends Author {

    List<String> roles;

    List<Category> categories;

    List<Tag> tags;
}
