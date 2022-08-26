package com.github.dge1992.fish.domain;

/**
 * @author dge
 * @version 1.0
 * @date 2021-10-11 13:59
 */
public class Car {

    /**
     * 品牌
     */
    private String brand;

    private Long creatDate;

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getCreatDate() {
        return this.creatDate;
    }

    public void setCreatDate(Long creatDate) {
        this.creatDate = creatDate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", creatDate=" + creatDate +
                '}';
    }
}
