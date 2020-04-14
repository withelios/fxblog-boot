package com.fxtahe.fxblog.vo;

import lombok.Data;

/**
 * @program fxblog-boot
 * @description 统一响应
 * @author fxtahe
 * @create 2020/04/14
 */
@Data
public class Result {

    private Integer code;
    private String message;
    private Object data;


    public Result(int code, String msg) {
        this.setCode(code);
        this.setMessage(msg);
    }

    public Result(ResponseStatus carStatus) {
        this.setCode(carStatus.getCode());
        this.setMessage(carStatus.getMsg());
    }

    public Result(int code, String msg, Object data) {
        this.setCode(code);
        this.setMessage(msg);
        this.setData(data);
    }

    public Result(ResponseStatus carStatus, Object data) {
        this.setCode(carStatus.getCode());
        this.setMessage(carStatus.getMsg());
        this.setData(data);
    }

    public static Result of(int code, String msg) {
        return new Result(code, msg);
    }

    public static Result of(ResponseStatus status) {
        return new Result(status);
    }

    public static Result of(int status, String msg, Object data) {
        return new Result(status, msg, data);
    }

    public static Result of(ResponseStatus carStatus, Object data) {
        return new Result(carStatus, data);
    }

    public static Result success() {
        return Result.of(ResponseStatus.OK);
    }

    public static Result success(Object data) {
        return Result.of(ResponseStatus.OK, data);
    }

    public static Result failure() {
        return Result.of(ResponseStatus.INTERNAL_SERVER_ERROR);
    }

    public static Result failure(String msg) {
        return Result.of(ResponseStatus.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    public static Result badRequest() {
        return Result.of(ResponseStatus.BAD_REQUEST);
    }

    public static Result badRequest(String msg) {
        return Result.of(ResponseStatus.BAD_REQUEST.getCode(), msg);
    }
}