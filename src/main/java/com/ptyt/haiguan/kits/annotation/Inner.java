package com.ptyt.haiguan.kits.annotation;

import java.lang.annotation.*;

/**
 * @author: yq
 * @date: 2019/11/15 11:45
 * @description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {
    /**
     * 是否AOP统一处理
     *
     * @return false, true
     */
    boolean value() default true;

    /**
     * 需要特殊判空的字段(预留)
     *
     * @return {}
     */
    String[] field() default {};
}
