package com.fxtahe.fxblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p></p>
 * @author fxtahe
 * @since 2020/8/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LogInfo {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 日志级别
     */
    private String logLevel;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 操作模块
     */
    private String operationModule;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 操作描述
     */
    private String operationDesc;

    /**
     * 请求地址
     */
    private String requestUri;

    /**
     * 请求IP
     */
    private String requestIp;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求入参
     */
    private String requestParameter;

    /**
     * 请求结果
     */
    private String requestResult;

    /**
     * 异常名称
     */
    private String excName;

    /**
     * 异常信息
     */
    private String excMessage;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createDate;


}
