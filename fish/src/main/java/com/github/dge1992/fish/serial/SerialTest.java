package com.github.dge1992.fish.serial;

import com.github.dge1992.fish.domain.Person;

import java.io.*;
import java.nio.file.Files;

/**
 * 测试对象在系列化和反序列化过程中不指定serialVersionUID会出现的异常
 */
public class SerialTest {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setName("Elliot");
        person.setAge(31);

        System.out.println(person);
        serialize(person);

        person = deserialize();
        System.out.println(person);
    }

    // 反序列化
    private static Person deserialize() throws Exception {
        ObjectInputStream objectInputStream =
                new ObjectInputStream(Files.newInputStream(
                        new File("/Users/ganendong/Documents/workspace/blog/Computer/person.txt").toPath()));
        return (Person) objectInputStream.readObject();
    }

    // 序列化
    private static void serialize(Person person) throws Exception {
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(Files.newOutputStream(
                        new File("/Users/ganendong/Documents/workspace/blog/Computer/person.txt").toPath()));
        objectOutputStream.writeObject(person);
        objectOutputStream.close();
    }
}
