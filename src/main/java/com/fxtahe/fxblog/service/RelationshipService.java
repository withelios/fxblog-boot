package com.fxtahe.fxblog.service;

import com.fxtahe.fxblog.entity.Relationship;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
public interface RelationshipService extends IService<Relationship> {


    void deleteByCondition(Relationship relationship);
}
