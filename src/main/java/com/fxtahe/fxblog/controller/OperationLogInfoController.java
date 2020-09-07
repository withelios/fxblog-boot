package com.fxtahe.fxblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.LogInfo;
import com.fxtahe.fxblog.service.OperationLogService;
import com.fxtahe.fxblog.util.Const;
import com.fxtahe.fxblog.vo.LogInfoVo;
import com.fxtahe.fxblog.vo.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fxtahe
 * @since 2020-08-19
 */
@Slf4j
@ResponseWrapper
@RestController
@RequestMapping("/admin/log")
public class OperationLogInfoController {


    @Resource
    private OperationLogService operationLogService;


    @PostMapping("/page")
    public Page<LogInfo> getLogInfoPage(@RequestBody PageRequest<LogInfoVo> request){
        long size = (request.getSize()==null ||request.getSize()<=0)? Const.PAGE_SIZE :request.getSize();
        long current = (request.getCurrent()==null ||request.getCurrent()<=0)?Const.PAGE_CURRENT:request.getCurrent();
        LogInfoVo data = request.getData();
        String id = data.getId();
        String level = data.getLogLevel();
        LocalDateTime startTime = data.getStartTime();
        LocalDateTime endTime = data.getEndTime();
        String userType = data.getUserType();
        String userName = data.getUserName();
        String operationModule = data.getOperationModule();
        String operationType = data.getOperationType();

        return operationLogService.page(
                new Page<LogInfo>().setSize(size).setCurrent(current),
                new QueryWrapper<LogInfo>()
                        .eq(id !=null,"id",id)
                        .eq(level !=null,"log_level",level)
                        .eq(userName!=null,"user_name",userName)
                        .eq(userType!=null,"user_type",userType)
                        .eq(operationModule !=null,"operation_module",operationModule)
                        .eq(operationType !=null,"operation_type",operationType)
                        .ge(startTime !=null,"create_date",startTime)
                        .le(endTime !=null,"create_date",endTime)
                        .orderByAsc("create_date"));

    }


}

