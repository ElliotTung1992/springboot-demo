package com.github.dge1992.restfuldemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 董感恩
 * @date 2020-02-24 17:26
 * @desc
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HttpResult {
    private Integer code;
    private String message;
}
