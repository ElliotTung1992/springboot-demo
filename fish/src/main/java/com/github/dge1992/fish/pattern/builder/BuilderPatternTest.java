package com.github.dge1992.fish.pattern.builder;

/**
 * 构建者模式
 */
public class BuilderPatternTest {

    public static void main(String[] args) {
        LenovoComputerBuilder lenovoComputerBuilder = new LenovoComputerBuilder("4", "三星125");
        Computer lenovoComputer = lenovoComputerBuilder
                .setUsbCount(4)
                .setDisplay("Lenovo Display")
                .setKeyboard("Lenovo Keyboard")
                .build();
        System.out.println(lenovoComputer);

        MacComputerBuilder macComputerBuilder = new MacComputerBuilder("6", "海力士");
        Computer MacComputer = macComputerBuilder
                .setDisplay("Mac Display")
                .setKeyboard("Mac Keyboard")
                .build();
        System.out.println(MacComputer);
    }
}
