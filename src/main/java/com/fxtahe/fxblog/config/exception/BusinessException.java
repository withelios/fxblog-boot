package com.fxtahe.fxblog.config.exception;

/**
* @description 自定义业务异常
* @author fxtahe
* @date 2020/6/18
*/
public class BusinessException extends RuntimeException {

    private String msg;

    public BusinessException(String msg){
        super(msg);
        this.msg = msg;
    }

    public BusinessException(String msg,Throwable throwable){
        super(msg,throwable);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
