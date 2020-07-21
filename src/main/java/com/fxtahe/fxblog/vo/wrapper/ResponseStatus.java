package com.fxtahe.fxblog.vo.wrapper;

import org.springframework.util.MimeType;

/**
 * @program fxblog-boot
 * @description 响应枚举类 {@link MimeType}
 * @author fxtahe
 * @create 2020-04-14
 */
public enum ResponseStatus {
    /**
     * 操作成功
     */
    OK(200,"操作成功"),
    /**
     * 无效的请求
     */
    BAD_REQUEST(400,"请求无效"),

    /**
     * 认证失败
     */
    AUTHENTICATE_ERROR(401,"认证失败"),

    /**
     * 权限不足
     */
    PERMISSION_DENIED_ERROR(403,"无权访问"),

    /**
     * 服务异常
     */
    INTERNAL_SERVER_ERROR(500,"服务异常"),

    /**
     * 登录信息异常
     */
    LOGIN_ERROR(40001,"账号或密码错误"),
    /**
     * token 过期
     */
    TOKEN_EXPIRED(10001,"Token 过期"),
    /**
     * 无效 token
     */
    ILLEGAL_TOKEN(10002,"无效 Token"),
    /**
     * token 异常
     */
    REFRESH_TOKEN_ERROR(10010,"Token刷新异常");



    private int code;
    private final String msg;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
