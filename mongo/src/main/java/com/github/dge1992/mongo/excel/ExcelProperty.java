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
public @interface ExcelProperty {

    //标题名
    String name();

    //是否必须
    boolean isRequired() default false;

    //长度
    int length() default 0;

    //序号
    int index() default 0;

    //列宽
    int columnWidth() default 3000;

    //是否写
    boolean isWrite() default false;

    //时间格式化
    String dateFormat() default "";
}
