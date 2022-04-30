package com.github.dge.es.model;

/**
 * @author dge
 * @version 1.0
 * @date 2021-10-25 20:37
 */
public class User {

    private String name;

    private Integer age;

    private String sex;

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

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
