package com.zhy.entity;

/**
 * 用户状态
 *
 * @author zhy
 * <p>
 * 2020年10月23日
 */
public enum UserStatus {

    normal("正常状态"), blocked("封禁状态");

    private final String info;

    private UserStatus(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
