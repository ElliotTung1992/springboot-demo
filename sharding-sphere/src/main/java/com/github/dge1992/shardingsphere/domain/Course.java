package com.github.dge1992.shardingsphere.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 董感恩
 * @date 2020-07-17 14:53
 * @desc
 */
@Data
@Accessors(chain = true)
public class Course {

    private Long cid;
    private String cname;
    private Long userId;
    private String status;
}
