package com.fxtahe.fxblog.vo;

import com.fxtahe.fxblog.entity.LogInfo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogInfoVo extends LogInfo {

    /**
     * 起时间
     */
    public LocalDateTime startTime;

    /**
     * 止时间
     */
    public LocalDateTime endTime;


}
