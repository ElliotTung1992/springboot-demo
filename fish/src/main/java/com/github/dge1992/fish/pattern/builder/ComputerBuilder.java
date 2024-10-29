package com.github.dge1992.fish.pattern.builder;

/**
 * 抽象构造函数
 * 功能:
 * 1. 设置默认值
 */
public abstract class ComputerBuilder {

    private Computer computer;

    public ComputerBuilder(String cpu, String ram) {
        this.computer = new Computer(cpu, ram);
        computer.setUsbCount(2);
    }

    public ComputerBuilder setUsbCount(Integer usbCount){
        computer.setUsbCount(usbCount);
        return this;
    }

    public ComputerBuilder setKeyboard(String keyboard){
        computer.setKeyboard(keyboard);
        return this;
    }

    public ComputerBuilder setDisplay(String display){
        computer.setDisplay(display);
        return this;
    }

    public Computer build(){
        return this.computer;
    }
}
