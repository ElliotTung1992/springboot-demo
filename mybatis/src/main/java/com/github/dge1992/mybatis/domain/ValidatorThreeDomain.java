package com.github.dge1992.mybatis.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author 董感恩
 * @date 2020-02-10 12:54
 * @desc 校验实体
 */
@Data
@NoArgsConstructor
public class ValidatorThreeDomain {
    @Size(min = 3,max = 5,message = "list的Size在[3,5]")
    private List<String> list;
    @NotNull
    @Valid
    private ValidatorFourDomain demo3;
}