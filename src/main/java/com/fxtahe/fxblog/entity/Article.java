package com.fxtahe.fxblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 作者
     */
    private Integer authorId;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String excerpt;

    /**
     * 内容
     */
    private String content;

    /**
     * markdown
     */
    private String markdown;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 分类id
     */
    private Integer categoryId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
