package com.github.elliot.springbootlearn.domain;

import com.github.elliot.springbootlearn.annotation.MapperBean;
import lombok.Data;

@MapperBean
@Data
public class PersonDomainMapperBean {

    private String name;

    private Integer age;
}
