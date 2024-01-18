package com.ellot1992.gateway.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

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
            if(Objects.equals(genderEnum.code, code)){
                return genderEnum;
            }
        }
        throw new RuntimeException(code + "未找到对应的GenderEnum类型");
    }
}
