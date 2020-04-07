package com.github.dge1992.application.lombok;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 董感恩
 * @date 2020-04-07 10:04
 * @desc
 */
@Data
@Accessors(chain = true)
public class LombokUser {
    private String name;
    private Integer age;

    public static void main(String[] args) {
        LombokUser lombokUser = new LombokUser();
        lombokUser.setAge(11).setName("dge");
        System.out.println(lombokUser);
    }
}
