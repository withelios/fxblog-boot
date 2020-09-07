package com.fxtahe.fxblog.entity;

import lombok.Data;

@Data
public class ArchiveArticle extends Article {


    private Integer year;

    private Integer month;

    private Integer day;
}
