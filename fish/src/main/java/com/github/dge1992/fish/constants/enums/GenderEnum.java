package com.github.dge1992.fish.constants.enums;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderEnum implements IEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    private final Integer code;
    private final String value;

    GenderEnum(Integer code, String value){
        this.code = code;
        this.value = value;
    }

    @JsonValue
    @JSONField(value = true)
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getCode() {
        return code;
    }

    @JsonCreator
    public static GenderEnum getEnum(Integer code) {
        for (GenderEnum genderEnum : GenderEnum.values()) {
            if(genderEnum.code == code){
                return genderEnum;
            }
        }
        return null;
    }
}
