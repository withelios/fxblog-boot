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
 * @since 2020-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role extends Model<Role> {

    private static final long serialVersionUID=1L;

    /**
     * 权限主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限定义
     */
    private String role;

    /**
     * 描述
     */
    private String desc;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
