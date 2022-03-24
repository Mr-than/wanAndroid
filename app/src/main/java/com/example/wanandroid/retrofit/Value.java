package com.example.wanandroid.retrofit;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Retention(RUNTIME)
@Target(METHOD)
@Repeatable(Values.class)
public @interface Value {

    String key() default "";
    String value() default "";

}
