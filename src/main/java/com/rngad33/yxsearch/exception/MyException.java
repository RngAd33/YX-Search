package com.rngad33.yxsearch.exception;

import com.rngad33.yxsearch.common.ErrorCode;

/**
 * 自定义异常类
 */
public class MyException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public MyException(int code, String message) {
        super(message);
        this.code = code;
    }

    public MyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public MyException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
