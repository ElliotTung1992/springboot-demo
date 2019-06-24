package com.github.dge1992.restfuldemo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/6/24
 **/
@Data
public class User {
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "年龄")
    private Integer age;
}
