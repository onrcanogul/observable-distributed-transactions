package com.starter.dtxspring.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Traceable {

    /**
     * Optional custom span name.
     * If empty, ClassName.methodName will be used.
     */
    String name() default "";
}
