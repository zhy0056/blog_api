package com.zhy.common.result;

/**
 * Controller参数校验，封装控制器参数校验失败的错误信息返回给客户端，
 * 主要包括字段名称 (fieldName) 和错误消息 (message) 两个属性
 *
 * @author zhy
 * <p>
 * 2020.11.02
 */
public class ParameterInvalidItem {

    private String fieldName;

    private String message;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
