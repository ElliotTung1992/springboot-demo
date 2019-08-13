package com.github.dge1992.mybatis.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/7
 **/
@TableName(value = "user")//对象进行CRUD是指定操作哪张表
@Data
public class User {
    @TableId(type = IdType.AUTO)//对象进行CRUD是指定表的主键
    private Integer id;
    //@TableField("user_name")//对象进行CRUD是指定表的字段,如开启驼峰映射不需要配置
    private String userName;
    private Integer age;
    @TableLogic
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT)
    private String des;
    @Version
    private Integer version;
}
