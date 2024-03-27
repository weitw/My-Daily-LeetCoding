package com.weitw.lc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

// leetCode题目名称
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // 假设这个注解是用在类上的
public @interface LCName {
    int index();
    String name() default "";
    String date();
}
