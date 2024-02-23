package com.github.dge1992.fish.domain.po;

import com.github.dge1992.fish.domain.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author dge
 * @version 1.0
 * @date 2021-03-30 11:19
 */
@Data
@NoArgsConstructor
public class PersonPo implements Serializable {

    private static final long serialVersionUID = -3977270282448869047L;

    private String name;

    private Integer age;

    private String sex;

    private BigDecimal money;

    private Car car;

    private List<PersonPo> offSpringList;

    private Map<String, String> map;

    private Integer gender;

    public PersonPo(String name) {
        this.name = name;
    }

    public boolean isHappy(String name){
        if("Bruce".equals(name)){
            return true;
        }
        return false;
    }

    public static boolean isSad(String name){
        if("Bruce".equals(name)){
            return true;
        }
        return false;
    }
}
