package com.github.dge1992.fish.spring.lifecycle.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-21 12:57 下午
 */
public class PersonBean implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    @Value("dge")
    private String name;

    @Value("11")
    private Integer age;

    public PersonBean() {
        System.out.println("1.【Spring生命周期】执行Person的无参构造函数");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        System.out.println("2.【Spring生命周期】设置对象属性");
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("3.【Spring生命周期】执行BeanNameAware的setBeanName方法");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("3.【Spring生命周期】执行BeanFactoryAware的setBeanFactory方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("4.2【Spring生命周期】执行InitializingBean的afterPropertiesSet方法");
    }

    private void initMethod() {
        System.out.println("4.3【Spring生命周期】执行initMethod方法");
    }

    private void destroyMethod() {
        System.out.println("6.【Spring生命周期】执行destroyMethod方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("5.【Spring生命周期】执行DisposableBean的destroy方法");
    }
}
