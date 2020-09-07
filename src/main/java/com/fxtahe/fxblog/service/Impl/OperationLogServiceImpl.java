package com.fxtahe.fxblog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxtahe.fxblog.entity.LogInfo;
import com.fxtahe.fxblog.mapper.LogInfoMapper;
import com.fxtahe.fxblog.service.OperationLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *     操作日志
 * </p>
 * @author fxtahe
 * @since 2020/8/19
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<LogInfoMapper, LogInfo> implements OperationLogService {


}
