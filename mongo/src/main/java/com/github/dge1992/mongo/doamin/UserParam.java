package com.github.dge1992.mongo.doamin;

import lombok.Data;

import java.util.List;

/**
 * @Author dongganene
 * @Description
 * @Date 2019/6/13
 **/
@Data
public class UserParam extends User{
    private Integer startAge;
    private Integer endAge;
    private List<User> users;
}
