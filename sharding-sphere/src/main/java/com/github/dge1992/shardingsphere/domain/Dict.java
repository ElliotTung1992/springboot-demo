package com.github.dge1992.shardingsphere.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 董感恩
 * @date 2020-07-17 17:10
 * @desc
 */
@Data
@TableName("t_dict")
@Accessors(chain = true)
public class Dict {
    private Long dictId;
    private String dictName;
    private String dictValue;
}
