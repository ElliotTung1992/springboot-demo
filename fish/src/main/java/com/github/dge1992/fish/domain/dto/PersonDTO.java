package com.github.dge1992.fish.domain.dto;

import com.github.dge1992.fish.constants.enums.GenderEnum;
import com.github.dge1992.fish.domain.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 4557455454358539771L;

    private String name;

    private Integer age;

    private String sex;

    private BigDecimal money;

    private Car car;

    private Map<String, String> map;

    private GenderEnum gender;

    public PersonDTO(String name) {
        this.name = name;
    }
}
