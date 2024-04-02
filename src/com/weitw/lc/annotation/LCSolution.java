package com.weitw.lc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// leetCode解法
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LCSolution {
    int index() default 1;  // 解题方法
    String name();  // 解题方法思路
    String date();
    String remark() default "";  // 解决方案描述
    String memory() default "";  // 内存超过多少人
    String time() default "";  // 执行用时超过多少人
}
