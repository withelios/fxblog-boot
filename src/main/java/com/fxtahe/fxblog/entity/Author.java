package com.fxtahe.fxblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fxtahe
 * @since 2020-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Author extends Model<Author> {

    private static final long serialVersionUID=1L;

    /**
     * 作者id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String authorName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
