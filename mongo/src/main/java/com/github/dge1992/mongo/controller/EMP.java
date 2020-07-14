package com.github.dge1992.mongo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 董感恩
 * @date 2020-03-23 10:16
 * @desc
 */
@Data
@AllArgsConstructor
public class EMP {
    Integer id;
    String name;
    Integer age;
    String gender;
    Integer salary;
}
