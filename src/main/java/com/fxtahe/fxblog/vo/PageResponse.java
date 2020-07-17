package com.fxtahe.fxblog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 *     自定义分页，mybatis-plus 分页插件查询 一对多场景时会出现统计条目问题及条件不支持问题
 *     比如，在根据文章标题，状态分类，及是否存在某标签等条件查询，文章-标签是一对多查询。
 *     方案一：使用mybatis 关联的嵌套 Select 查询 可以解决一对多查询条目问题，但是会出现1+N
 *     的问题且无法构造标签 传参的条件
 *     方案二：自定义分页，二次统计条目
 * </p>
* @description 分页响应
* @author fxtahe
* @date 2020/5/12
*/
@Data
@Accessors(chain = true)
public class PageResponse<T> {

    private Long current;

    private Long size;

    private Long total;

    private List<T> data;



}
