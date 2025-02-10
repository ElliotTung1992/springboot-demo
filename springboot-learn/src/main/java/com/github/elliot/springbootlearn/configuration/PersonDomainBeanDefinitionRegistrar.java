package com.github.elliot.springbootlearn.configuration;

import com.github.elliot.springbootlearn.domain.ImportPersonDomain;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class PersonDomainBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(ImportPersonDomain.class)
                .addPropertyValue("name", "Bruce")
                .getBeanDefinition();
        registry.registerBeanDefinition("importPersonDomain", beanDefinition);
    }
}
