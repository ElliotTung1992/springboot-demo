package com.github.dge1992.mybatis.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author 董感恩
 * @date 2020-02-10 12:54
 * @desc 校验实体
 */
@Data
@NoArgsConstructor
public class ValidatorFourDomain {
    @Length(min = 5, max = 17, message = "length长度在[5,17]之间")
    private String extField;
}