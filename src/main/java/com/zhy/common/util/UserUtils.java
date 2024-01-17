package com.zhy.common.util;

import org.apache.shiro.SecurityUtils;

import com.zhy.common.constant.Base;
import com.zhy.entity.User;

public class UserUtils {

    public static User getCurrentUser() {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Base.CURRENT_USER);
        return user;
    }
}
