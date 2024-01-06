package com.github.dge1992.fish.domain;


import java.io.Serializable;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2021-10-14 11:42
 */
public class A implements Serializable {

    private String name;

    private List<Integer> ids;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIds() {
        return this.ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
