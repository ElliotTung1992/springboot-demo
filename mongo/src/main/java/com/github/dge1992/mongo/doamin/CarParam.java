package com.github.dge1992.mongo.doamin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/6/21
 **/
@Data
public class CarParam {
    @ApiModelProperty(value = "主键", required = true)
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "车上的乘客")
    private String[] users;
    @ApiModelProperty(value = "车上的狗")
    private List<Dog> dogs;
}
