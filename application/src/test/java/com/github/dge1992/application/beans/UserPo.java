package com.github.dge1992.application.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 董感恩
 * @date 2020-04-07 10:17
 * @desc
 */
@AllArgsConstructor
@Data
public class UserPo {
    private String name;
    private Integer age;
    private String address;
}
