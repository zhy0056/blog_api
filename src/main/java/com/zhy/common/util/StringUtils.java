package com.zhy.common.util;

public class StringUtils {

    //用于判断字符串是否为null或空字符串
    public static boolean isEmpty(String value) {
        if (null == value)
            return true;
        return value.isEmpty();
    }
}
