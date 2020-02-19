package com.github.dge1992.mybatis.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author 董感恩
 * @date 2020-02-10 13:19
 * @desc 校验器配置
 */
@Configuration
public class ValidatorConfig {

    /**
     * @author 董感恩
     * @date 2020-02-10 13:20:12
     * @desc hibernate校验模式-（快速/普通）失败返回模式 true-快速 false-普通
     **/
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "false" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        //设置校验模式
        postProcessor.setValidator(validator());
        return postProcessor;
    }
}
