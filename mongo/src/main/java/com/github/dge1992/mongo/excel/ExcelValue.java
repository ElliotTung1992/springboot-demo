package com.github.dge1992.mongo.excel;

import java.lang.annotation.*;

/**
 * @author 董感恩
 * @date 2019-12-29 17:11
 * @desc Excel属性注解
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelValue {

    String name();

    boolean isRequired() default false;

    int length() default 0;
}
