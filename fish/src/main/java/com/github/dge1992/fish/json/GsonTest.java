package com.github.dge1992.fish.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.*;

public class GsonTest {

    public static void main(String[] args) {
        // primitivesExamples();
        // objectExamples();
        // arrayExamples();
        // collectionsExamples();
        mapsExamples();
    }

    private static void mapsExamples() {
        // Map序列化
        Map<String, String> map = new HashMap<>();
        map.put("name", "xiaoming");
        map.put("sex", "male");

        Gson gson = new Gson();
        System.out.println(gson.toJson(map));

        TypeToken<HashMap<String, String>> typeToken = new TypeToken<HashMap<String, String>>(){};
        HashMap<String, String> stringHashMap = gson.fromJson("{\"sex\":\"male\",\"name\":\"xiaoming\"}", typeToken);
        System.out.println(stringHashMap);
    }

    private static void collectionsExamples() {
        // 集合序列化
        Gson gson = new Gson();
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        System.out.println(gson.toJson(list));

        TypeToken<ArrayList<Integer>> collectionType = new TypeToken<ArrayList<Integer>>(){};
        ArrayList<Integer> integers = gson.fromJson("[1,2,3,4]", collectionType);
        System.out.println(integers);
    }

    private static void arrayExamples() {
        // 数组
        Gson gson = new Gson();
        int[] ints = {1,2,3,4,5};
        String[] strings = {"a", "b", "c", "d"};
        System.out.println(gson.toJson(ints));
        System.out.println(gson.toJson(strings));

        String intsJson = "[1,2,3,4,5]";
        String stringsJson = "[\"a\",\"b\",\"c\",\"d\"]";
        int[] ints1 = gson.fromJson(intsJson, int[].class);
        System.out.println(Arrays.toString(ints1));
        String[] strings1 = gson.fromJson(stringsJson, String[].class);
        System.out.println(Arrays.toString(strings1));

    }

    private static void objectExamples() {
        // 对象类型序列化
        BagOfPrimitives obj = new BagOfPrimitives();
        Gson gson = new Gson();
        System.out.println(gson.toJson(obj));

        String json = "{\"value1\":2,\"value2\":\"abcd\"}";
        BagOfPrimitives bagOfPrimitives = gson.fromJson(json, BagOfPrimitives.class);
        System.out.println(bagOfPrimitives);
    }


    private static void primitivesExamples() {
        // 基本类型对象
        // Serialization
        Gson gson = new Gson();
        System.out.println(gson.toJson(1));
        System.out.println(gson.toJson("abcd"));
        System.out.println(gson.toJson(new Long(10)));
        int[] arr = {1};
        System.out.println(gson.toJson(arr));

        // Deserialization
        System.out.println(gson.fromJson("1", int.class));
        System.out.println(gson.fromJson("1", Integer.class));
        System.out.println(gson.fromJson("1", Long.class));
        System.out.println(gson.fromJson("true", Boolean.class));
        System.out.println(gson.fromJson("True", Boolean.class));
        System.out.println(gson.fromJson("\"abc\"", String.class));
        System.out.println(Arrays.toString(gson.fromJson("[\"abc\", \"hello\"]", String[].class)));
    }
}

class BagOfPrimitives {
    private int value1 = 1;
    private String value2 = "abc";
    private transient int value3 = 3;
    BagOfPrimitives() {
        // no-args constructor
    }

    @Override
    public String toString() {
        return "BagOfPrimitives{" +
                "value1=" + value1 +
                ", value2='" + value2 + '\'' +
                ", value3=" + value3 +
                '}';
    }
}
