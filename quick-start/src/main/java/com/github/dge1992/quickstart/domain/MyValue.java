package com.github.dge1992.quickstart.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/8
 **/
@Data
@Component
public class MyValue {

    @Value("${com.github.dge1992.quickstart.author}")
    private String author;
    @Value("${com.github.dge1992.quickstart.date}")
    private String date;
    @Value("${com.github.dge1992.quickstart.desc}")
    private String desc;
    @Value("${com.github.dge1992.quickstart.value}")
    private String value;
    @Value("${com.github.dge1992.quickstart.number}")
    private String number;
    @Value("${com.github.dge1992.quickstart.bigNumber}")
    private String bigNumber;
    @Value("${com.github.dge1992.quickstart.test1}")
    private String test1;
    @Value("${com.github.dge1992.quickstart.test2}")
    private String test2;
}
