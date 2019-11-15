package com.github.dge1992.mybatis.mutidatasource.annotion;

import java.lang.annotation.*;

/**
 *
 * @author 小眼睛带鱼
 * @date 2019-11-15 11:06:17
 * @desc 多数据源标识
 **/
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface DataSource {

	String name() default "";
}
