package com.fxtahe.fxblog.vo.wrapper;

import org.springframework.util.MimeType;

/**
 * @program fxblog-boot
 * @description 响应枚举类 {@link MimeType}
 * @author fxtahe
 * @create 2020-04-14
 */
public enum ResponseStatus {

    OK(200,"操作成功"),
    BAD_REQUEST(401,"请求无效"),
    INTERNAL_SERVER_ERROR(500,"服务异常");

    private int code;
    private String msg;

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
