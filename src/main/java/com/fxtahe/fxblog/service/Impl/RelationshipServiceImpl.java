package com.fxtahe.fxblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxtahe.fxblog.entity.Relationship;
import com.fxtahe.fxblog.mapper.RelationshipMapper;
import com.fxtahe.fxblog.service.RelationshipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
@Service
public class RelationshipServiceImpl extends ServiceImpl<RelationshipMapper, Relationship> implements RelationshipService {

    @Override
    public void deleteByCondition(Relationship relationship) {
        remove(
                new QueryWrapper<Relationship>()
                .eq(relationship.getId() != null,"id",relationship.getId())
                .eq(relationship.getRType() != null,"r_id",relationship.getRId())
                .eq(relationship.getArticleId() != null,"article_id",relationship.getArticleId())
                .eq(relationship.getRType() != null,"r_type",relationship.getRType())
        );
    }
}
