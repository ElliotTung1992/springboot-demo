package com.github.dge1992.mybatis.service.impl;

import com.github.dge1992.mybatis.service.IValidatorService;
import org.springframework.stereotype.Service;

/**
 * @author 董感恩
 * @date 2020-02-24 10:16
 * @desc
 */
@Service
public class ValidatorServiceImpl implements IValidatorService {

    @Override
    public void checkService(String s) {
        System.out.println(s);
    }
}
