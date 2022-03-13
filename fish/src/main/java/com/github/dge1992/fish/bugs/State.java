package com.github.dge1992.fish.bugs;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-13 9:20 下午
 */
public enum State {

    ON{
        @Override
        String getName() {
            return "开";
        }
    },
    OFF{
        @Override
        String getName() {
            return "关";
        }
    };

    abstract String getName();
}
