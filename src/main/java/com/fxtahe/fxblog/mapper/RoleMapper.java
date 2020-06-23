package com.fxtahe.fxblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxtahe.fxblog.entity.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fxtahe
 * @since 2020-06-17
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectRoleByUserId(Integer id);
}
