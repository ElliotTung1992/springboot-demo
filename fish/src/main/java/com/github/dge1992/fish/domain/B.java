package com.github.dge1992.fish.domain;

import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2021-10-14 11:43
 */
public class B {

    private String name;

    private List<String> ids;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIds() {
        return this.ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "B{" +
                "name='" + name + '\'' +
                ", ids=" + ids +
                '}';
    }
}
