package com.github.dge1992.fish.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author dge
 * @version 1.0
 * @date 2021-03-30 11:19
 */
public class Person implements Serializable {

    private static final long serialVersionUID = -3977270282448869047L;

    private String name;

    private Integer age;

    private String sex;

    private BigDecimal money;

    private Car car;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", money=" + money +
                ", car=" + car +
                '}';
    }
}
