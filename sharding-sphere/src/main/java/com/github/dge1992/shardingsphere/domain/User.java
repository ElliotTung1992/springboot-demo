package com.github.dge1992.shardingsphere.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 董感恩
 * @date 2020-07-17 16:58
 * @desc
 */
@Data
@TableName("t_user")
@Accessors(chain = true)
public class User {
    private Long userId;
    private String username;
    private Integer age;
}
