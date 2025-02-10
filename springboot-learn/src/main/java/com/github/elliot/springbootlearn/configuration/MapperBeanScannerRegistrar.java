package com.github.elliot.springbootlearn.configuration;

import com.github.elliot.springbootlearn.annotation.MapperBean;
import com.github.elliot.springbootlearn.annotation.MapperBeanScan;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class MapperBeanScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 获取注解属性 - 包路径属性集合
        AnnotationAttributes annotationAttributes =
                AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(MapperBeanScan.class.getName()));
        String[] basePackages = (String[]) annotationAttributes.get("basePackages");
        // 创建扫描器
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry, false);
        scanner.setResourceLoader(resourceLoader);
        // 只把路径下配置@MapperBean.class注解的Bean注入
        scanner.addIncludeFilter(new AnnotationTypeFilter(MapperBean.class));
        // 设置扫描路径
        scanner.scan(basePackages);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
