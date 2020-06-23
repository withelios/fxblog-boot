package com.fxtahe.fxblog.vo;

import com.fxtahe.fxblog.entity.Author;
import lombok.Data;

import java.util.List;

/**
* @description TODO
* @author fxtahe
* @date 2020/6/22
*/
@Data
public class AuthorVo extends Author {

    List<String> roles;
}
