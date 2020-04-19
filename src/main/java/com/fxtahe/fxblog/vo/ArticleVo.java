package com.fxtahe.fxblog.vo;

import com.fxtahe.fxblog.entity.Article;
import com.fxtahe.fxblog.entity.Category;
import com.fxtahe.fxblog.entity.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @program fxblog-boot
 * @description
 * @author fxtahe
 * @create 2020/04/15
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleVo extends Article {


    public List<Tag> tags;

    public Category category;


}
