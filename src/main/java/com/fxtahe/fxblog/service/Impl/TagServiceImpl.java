package com.fxtahe.fxblog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxtahe.fxblog.entity.Relationship;
import com.fxtahe.fxblog.entity.Tag;
import com.fxtahe.fxblog.mapper.TagMapper;
import com.fxtahe.fxblog.service.RelationshipService;
import com.fxtahe.fxblog.service.TagService;
import com.fxtahe.fxblog.util.Const;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fxtahe
 * @since 2020-04-15
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    private RelationshipService relationshipService;

    @Override
    public void deleteTag(Integer id) {
        this.removeById(id);
        relationshipService.deleteByCondition(new Relationship().setRId(id).setRType(Const.TAG_TYPE));
    }
}
