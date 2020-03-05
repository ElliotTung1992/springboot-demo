package com.github.dge1992.mybatis.service;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * @author 董感恩
 * @date 2020-02-24 10:12
 * @desc
 */
@Validated
public interface IValidatorService {

    void checkService(@NotEmpty(message = "机构号不能为空") String s);
}
