package com.fxtahe.fxblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 *  作者信息
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

    @NotEmpty(message = "The author name is not allowed to be empty")
    private String authorName;
    @JsonIgnore
    @NotEmpty(message = "The password is not allowed to be empty")
    private String password;

    private String avatar;

    private String github;

    private String email;

    private String introduction;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
