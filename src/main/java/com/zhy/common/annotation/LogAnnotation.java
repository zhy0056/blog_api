package com.zhy.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义日志注解类
 *
 * @author zhy
 * <p>
 * 2020.11.03
 */
//表示只能用于方法级别
@Target(ElementType.METHOD)
//运行时保留，以便切面通过反射获取信息
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    //记录业务模块
    String module() default "";

    //记录操作
    String operation() default "";
}
