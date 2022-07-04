package com.sinosoft.baiduai.util;

import java.io.Serializable;

/**
 * @author hiYuzu
 * @version v1.0
 * @date 2022/5/30 12:13
 */
public class ResultUtil<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success = true;

    /**
     * 返回处理消息
     */
    private String message = "";

    /**
     * 返回代码
     */
    private Integer code = 0;

    /**
     * 返回数据对象 data
     */
    private T result;

    public ResultUtil() {
    }

    public static <T> ResultUtil<T> ok(T data) {
        ResultUtil<T> r = new ResultUtil<T>();
        r.setSuccess(true);
        r.setCode(GlobalUtil.SC_OK_200);
        r.setResult(data);
        return r;
    }

    public static <T> ResultUtil<T> error(String msg) {
        ResultUtil<T> r = new ResultUtil<T>();
        r.setSuccess(false);
        r.setCode(GlobalUtil.SC_INTERNAL_SERVER_ERROR_500);
        r.setMessage(msg);
        r.setResult(null);
        return r;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
