/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.common.enums;

/**
 *
 * @author Shenjizhe
 */
public enum ErrorCode {

    Success(0, "成功"),
    Fail(1, "失败"),
    DataSourceNotFound(101, "数据源不存在"),
    TableNotContains(102, "资源不存在"),
    ColumnNotContains(103, "资源属性不存在"),
    ConditionFail(151, "查询条件不合法"),
    perator(201, "用户不具有操作权限"),
    UserCantOperator(201, "用户不具有操作权限"),
    UserCantResource(202, "用户不能操作资源"),
    ResourceNotRecognize(301, "不能识别资源类型"),
    ResourceNotFound(302, "资源不存在"),;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
