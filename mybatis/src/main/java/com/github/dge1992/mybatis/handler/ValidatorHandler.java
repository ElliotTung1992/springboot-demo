package com.github.dge1992.mybatis.handler;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author 董感恩
 * @date 2020-03-05 12:44
 * @desc 校验器
 */
public class ValidatorHandler<T> {

    @Autowired
    private Validator validator;

    public String validate(T t){
        StringBuilder stringBuilder = new StringBuilder();
        Set<ConstraintViolation<T>> validate =
                validator.validate(t);
        for (ConstraintViolation<T> model : validate) {
            stringBuilder.append(model.getMessage()).append(" ");
        }
        if(stringBuilder.length() > 0){
            return stringBuilder.toString();
        }
        return null;
    }
}
