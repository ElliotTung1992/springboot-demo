package com.github.elliot.springbootlearn.annotation;

import com.github.elliot.springbootlearn.configuration.MapperBeanScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MapperBeanScannerRegistrar.class)
public @interface MapperBeanScan {

    String[] basePackages() default {};
}
