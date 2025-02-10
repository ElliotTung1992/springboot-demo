package com.github.elliot.springbootlearn.annotation;

import com.github.elliot.springbootlearn.configuration.CatchServiceImportSelector;
import com.github.elliot.springbootlearn.enums.CatchTypeEnum;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CatchServiceImportSelector.class)
public @interface EnableCatch {
    CatchTypeEnum catchType() default CatchTypeEnum.LOCAL;
}
