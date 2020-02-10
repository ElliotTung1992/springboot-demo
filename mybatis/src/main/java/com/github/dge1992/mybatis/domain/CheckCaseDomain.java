package com.github.dge1992.mybatis.domain;

import com.github.dge1992.mybatis.domain.validate.CaseMode;
import com.github.dge1992.mybatis.domain.validate.CheckCase;
import lombok.Data;

/**
 * @author 董感恩
 * @date 2020-02-10 16:27
 * @desc
 */
@Data
public class CheckCaseDomain {
    @CheckCase(value = CaseMode.LOWER, message = "用户名必须为小写")
    private String userName;
}
