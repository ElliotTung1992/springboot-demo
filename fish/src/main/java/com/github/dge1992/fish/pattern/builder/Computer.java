package com.github.dge1992.fish.pattern.builder;

import lombok.Data;

@Data
public class Computer {

    private String cpu;      // 必须
    private String ram;      // 必须
    private Integer usbCount;    // 可选
    private String keyboard; // 可选
    private String display;  // 可选

    public Computer(String cpu, String ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

}
