package com.github.dge1992.mybatis.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author 董感恩
 * @date 2020-02-10 12:54
 * @desc 校验实体
 */
@Data
@NoArgsConstructor
public class ValidatorDomain {
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "年龄不能为空")
    @Pattern(regexp = "^[0-9]{1,2}$", message = "年龄格式不正确")
    private String age;
    @AssertTrue(message = "性别必须为男")
    private Boolean gender;
    @Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$",message="出生日期格式不正确")
    private String birthday;
}
