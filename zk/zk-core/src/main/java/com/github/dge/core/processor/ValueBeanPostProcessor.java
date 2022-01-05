package com.github.dge.core.processor;

import com.github.dge.core.cache.ValueBeanDefinitionContainer;
import com.github.dge.core.cache.ValueBeanDefinitionObject;
import com.github.dge.core.instance.ConfigCenterManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-14 5:16 PM
 */
@ConditionalOnProperty(prefix = "fish.config-center", name = "enable", havingValue = "true")
@Component
public class ValueBeanPostProcessor implements BeanPostProcessor , BeanFactoryAware, Ordered {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ValueBeanDefinitionContainer instance = ValueBeanDefinitionContainer.getInstance();
        ReflectionUtils.doWithFields(bean.getClass(), (field) -> {
            if (field.isAnnotationPresent(Value.class)) {
                ValueBeanDefinitionObject valueBeanDefinitionObject = new ValueBeanDefinitionObject();
                String fieldName = field.getName();
                valueBeanDefinitionObject.setFieldName(fieldName);
                valueBeanDefinitionObject.setBeanName(beanName);
                ArrayList<ValueBeanDefinitionObject> list = new ArrayList<>();
                list.add(valueBeanDefinitionObject);
                instance.set(fieldName,list);
            }
        });
        return bean;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 3;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        ConfigCenterManager.setBeanFactory(beanFactory);
    }
}
