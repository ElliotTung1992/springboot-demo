package com.github.dge.fishspringbootstarter.cache;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-14 5:44 PM
 */
public class ValueBeanDefinitionObject {

    private String fieldName;

    private String beanName;

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getBeanName() {
        return this.beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String toString() {
        return "ValueBeanDefinitionObject{" +
                "fieldName='" + fieldName + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }
}
