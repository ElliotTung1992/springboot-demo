package com.github.dge1992.fish.domain.po;

import lombok.Data;

@Data
public class PeoplePo {

    private boolean isMale;

    public String getName(){
        return "Bruce";
    }
}
