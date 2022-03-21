package com.github.dge1992.fish.spring.lifecycle.processor;

import com.github.dge1992.fish.spring.lifecycle.bean.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-21 1:15 下午
 */
@Component
public class PersonBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(Person.class.getName().equals(bean.getClass().getName())){
            System.out.println("4.1【Spring生命周期】执行PersonBeanPostProcessor的前置处理函数");
            System.out.println(bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(Person.class.getName().equals(bean.getClass().getName())){
            System.out.println("4.4【Spring生命周期】执行PersonBeanPostProcessor的后置处理函数");
        }
        return bean;
    }
}
