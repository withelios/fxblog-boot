package com.fxtahe.fxblog.entity;

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
 * @since 2020-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Website extends Model<Website> {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String about;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
