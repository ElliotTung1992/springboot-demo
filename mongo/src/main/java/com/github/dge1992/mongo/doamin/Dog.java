package com.github.dge1992.mongo.doamin;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @Author dongganene
 * @Description
 * @Date 2019/6/13
 **/
@Data
public class Dog {
    @Id
    private Integer id;
    private String varieties;
}
