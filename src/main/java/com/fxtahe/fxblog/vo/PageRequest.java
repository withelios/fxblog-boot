package com.fxtahe.fxblog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
* @description PageRequest
* @author fxtahe
* @date 2020/5/11
*/
@Data
@Accessors(chain = true)
public class PageRequest<T> {

    private Long current;

    private Long size;

    private Long limit;

    private T data;
}
