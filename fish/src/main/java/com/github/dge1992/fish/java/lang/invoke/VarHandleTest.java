package com.github.dge1992.fish.java.lang.invoke;

import com.github.dge1992.fish.domain.DomeObject;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-27 11:21
 */
public class VarHandleTest {

    public static void main(String[] args) throws Exception {

        testPrivate();

        testProtected();

        testPublic();

        testArr();
    }

    /**
     * 获取数组变量
     * @author dge
     * @date 2021-01-27 14:42
     */
    private static void testArr() {
        DomeObject instance = new DomeObject();
        VarHandle arrayVarHandle = MethodHandles.arrayElementVarHandle(int[].class);
        arrayVarHandle.compareAndSet(instance.arrayData, 0, 1, 11);
        arrayVarHandle.compareAndSet(instance.arrayData, 1, 2, 22);
        arrayVarHandle.compareAndSet(instance.arrayData, 2, 3, 33);
        System.out.println(instance);
    }

    /**
     * 获取public成员
     * @author dge
     * @date 2021-01-27 14:41
     */
    private static void testPublic() throws NoSuchFieldException, IllegalAccessException {
        DomeObject instance = new DomeObject();
        VarHandle varHandle = MethodHandles.lookup()
                .in(DomeObject.class)
                .findVarHandle(DomeObject.class, "publicVar", int.class);
        varHandle.set(instance, 11);
        System.out.println(instance);
    }

    /**
     * 获取访问Protected变量
     * @author dge
     * @date 2021-01-27 14:38
     */
    private static void testProtected() throws IllegalAccessException, NoSuchFieldException {
        DomeObject instance = new DomeObject();

        VarHandle varHandle = MethodHandles.privateLookupIn(DomeObject.class,MethodHandles.lookup())
                .findVarHandle(DomeObject.class, "protectedVar", int.class);

        /*VarHandle varHandle = MethodHandles.lookup()
                .in(DomeObject.class)
                .findVarHandle(DomeObject.class, "protectedVar", int.class);*/
        varHandle.set(instance, 22);
        System.out.println(instance);
    }

    /**
     * 测试访问私有变量
     * @author dge
     * @date 2021-01-27 14:26
     */
    private static void testPrivate() throws IllegalAccessException, NoSuchFieldException {
        DomeObject object = new DomeObject();

        VarHandle varHandle = MethodHandles.privateLookupIn(DomeObject.class, MethodHandles.lookup())
                .findVarHandle(DomeObject.class, "privateVar", int.class);

        varHandle.set(object, 55);

        System.out.println(object);
    }


}
