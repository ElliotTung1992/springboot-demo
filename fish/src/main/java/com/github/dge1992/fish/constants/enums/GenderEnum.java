package com.github.dge1992.fish.constants.enums;

public enum GenderEnum implements IEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    private final Integer code;
    private final String value;

    GenderEnum(Integer code, String value){
        this.code = code;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getCode() {
        return code;
    }
}
