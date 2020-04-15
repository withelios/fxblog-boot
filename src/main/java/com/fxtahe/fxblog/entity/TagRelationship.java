package com.fxtahe.fxblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

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
public class TagRelationship extends Model<TagRelationship> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer tagId;

    private Integer articleId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
