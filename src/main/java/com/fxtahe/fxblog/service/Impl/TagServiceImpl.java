package com.fxtahe.fxblog.service.Impl;

import com.fxtahe.fxblog.entity.Tag;
import com.fxtahe.fxblog.mapper.TagMapper;
import com.fxtahe.fxblog.service.TagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
