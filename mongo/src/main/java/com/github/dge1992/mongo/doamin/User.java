package com.github.dge1992.mongo.doamin;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @Author dongganene
 * @Description
 * @Date 2019/6/13
 **/
@Data
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private List<Dog> dogs;
    private String phone;
}
