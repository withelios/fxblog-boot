package com.fxtahe.fxblog.vo;

import com.fxtahe.fxblog.entity.Article;
import com.fxtahe.fxblog.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo extends Category {

    private List<Article> articles;
}
