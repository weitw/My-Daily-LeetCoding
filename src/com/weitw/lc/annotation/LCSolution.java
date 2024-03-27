package com.weitw.lc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// leetCode解法
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LCSolution {
    int index();
    String name();
    String date();
    String remark() default "";  // 解决方案描述
}
