package com.github.dge1992.fish.stream;

import com.github.dge1992.fish.domain.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dge
 * @version 1.0
 * @date 2022-07-05 3:09 PM
 */
public class SortTest {

    public static void main(String[] args) {
        List<Car> carList = new ArrayList<>();
        Car car1 = new Car();
        car1.setBrand("bnez");
        car1.setCreatDate(1L);
        Car car2 = new Car();
        car2.setBrand("bmw");
        car2.setCreatDate(2L);
        Car car3 = new Car();
        car3.setBrand("toto");
        car3.setCreatDate(1L);
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);

        for (Car car : carList) {
            System.out.println(car);
        }

        carList = carList.stream()
                .sorted(Comparator.comparing(Car::getCreatDate).reversed())
                .collect(Collectors.toList());

        for (Car car : carList) {
            System.out.println(car);
        }
    }
}
