package com.github.dge1992.fish.thread;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * @author dge
 * @version 1.0
 * @date 2021-02-25 21:21
 */
public class VarHandleTest {

    /**
     * 普通字符串
     */
    private String str;
    /**
     * 静态字符串
     */
    private static String staticStr;
    /**
     * 反射字符串
     */
    private String reflectStr;
    /**
     * 字符串数组
     */
    private String[] arrayStr = new String[10];

    volatile Integer i;

    private static final VarHandle VAR_STR;

    private static final VarHandle VAR_STATIC_STR;

    private static final VarHandle VAR_REFLECT_STR;

    private static final VarHandle VAR_ARRAY_STR;

    private static final VarHandle VAR_INT;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            VAR_STR = lookup.findVarHandle(VarHandleTest.class, "str", String.class);
            VAR_STATIC_STR = lookup.findStaticVarHandle(VarHandleTest.class, "staticStr", String.class);
            VAR_REFLECT_STR = lookup.unreflectVarHandle(VarHandleTest.class.getDeclaredField("reflectStr"));
            VAR_ARRAY_STR = MethodHandles.arrayElementVarHandle(String[].class);

            VAR_INT = lookup.findVarHandle(VarHandleTest.class, "i", Integer.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void main(String[] args) {
        VarHandleTest varHandleTest = new VarHandleTest();
        varHandleTest.caseOne();
    }

    private void caseOne() {
        // 普通字符串read/write
        VAR_STR.set(this, "hello str");
        String var_str_print = (String) VAR_STR.get(this);
        System.out.println(str);
        System.out.println(var_str_print);

        // 静态字符串的read/write
        VAR_STATIC_STR.set("hello static str");
        String var_static_str_print = (String) VAR_STATIC_STR.get();
        System.out.println(staticStr);
        System.out.println(var_static_str_print);

        // 反射字段的read/write
        VAR_REFLECT_STR.set(this, "hello reflect str");
        String var_reflect_str_print = (String) VAR_REFLECT_STR.get(this);
        System.out.println(var_reflect_str_print);
        System.out.println(var_reflect_str_print);

        VAR_ARRAY_STR.set(arrayStr, 0, "arrayStr index 0 is hello");
        String var_array_str_print = (String) VAR_ARRAY_STR.get(arrayStr, 0);
        System.out.println(var_array_str_print);
        System.out.println(arrayStr[0]);

        VAR_INT.set(this, 20);
        Integer i_print = (Integer) VAR_INT.get(this);
        System.out.println(i_print);
        System.out.println(i);

        VAR_INT.compareAndSet(this, 19, 21);
        System.out.println(i);

        VAR_INT.compareAndSet(this, 20, 21);
        System.out.println(i);
    }
}
