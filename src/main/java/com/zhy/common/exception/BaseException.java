package com.zhy.common.exception;

/**
 * 自定义异常类，方便自定义信息和错误码
 *
 * @author zhy
 * <p>
 * 2020.11.04
 */
public class BaseException extends RuntimeException {

    //将异常对象序列化到文件或者网络传输，然后在不同的 JVM 中进行反序列化，就能够保证版本的兼容性。
    private static final long serialVersionUID = 3506744187536228284L;

    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public BaseException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

}
