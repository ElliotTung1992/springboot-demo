package com.github.dge1992.mybatis.domain.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 董感恩
 * @date 2020-02-10 16:22
 * @desc 大小写验证器
 */
public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

    private CaseMode caseMode;

    public void initialize(CheckCase checkCase) {
        this.caseMode = checkCase.value();
    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        if (caseMode == CaseMode.UPPER) {
            return s.equals(s.toUpperCase());
        } else {
            return s.equals(s.toLowerCase());
        }
    }
}
