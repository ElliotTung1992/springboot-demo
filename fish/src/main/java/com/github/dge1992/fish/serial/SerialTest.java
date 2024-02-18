package com.github.dge1992.fish.serial;

import com.github.dge1992.fish.domain.po.PersonPo;

import java.io.*;
import java.nio.file.Files;

/**
 * 测试对象在系列化和反序列化过程中不指定serialVersionUID会出现的异常
 */
public class SerialTest {

    public static void main(String[] args) throws Exception {
        PersonPo personPo = new PersonPo();
        personPo.setName("Elliot");
        personPo.setAge(31);

        System.out.println(personPo);
        serialize(personPo);

        personPo = deserialize();
        System.out.println(personPo);
    }

    // 反序列化
    private static PersonPo deserialize() throws Exception {
        ObjectInputStream objectInputStream =
                new ObjectInputStream(Files.newInputStream(
                        new File("/Users/ganendong/Documents/workspace/blog/Computer/person.txt").toPath()));
        return (PersonPo) objectInputStream.readObject();
    }

    // 序列化
    private static void serialize(PersonPo personPo) throws Exception {
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(Files.newOutputStream(
                        new File("/Users/ganendong/Documents/workspace/blog/Computer/person.txt").toPath()));
        objectOutputStream.writeObject(personPo);
        objectOutputStream.close();
    }
}
