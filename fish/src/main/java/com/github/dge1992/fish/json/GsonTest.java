package com.github.dge1992.fish.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.*;

public class GsonTest {

    static Gson gson = new Gson();

    public static void main(String[] args) {
        // primitivesExamples();
        objectExamples();
        arrayExamples();
        collectionsExamples();
        mapsExamples();
    }

    private static void mapsExamples() {
        // Map案例
        Map<String, String> map = new HashMap<>();
        map.put("name", "Elliot");
        map.put("address", "Ningbo");

        // 序列化
        String mapJson = gson.toJson(map);
        System.out.println(mapJson);

        // 反序列化
        TypeToken<Map<String, String>> typeToken = new TypeToken<Map<String, String>>(){};
        Map<String, String> gsonMap = gson.fromJson(mapJson, typeToken);
        System.out.println(gsonMap);
    }

    private static void collectionsExamples() {
        // 集合案例
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // 序列化

        String gsonString = gson.toJson(list);
        System.out.println(gsonString);

        // 反序列化
        TypeToken<ArrayList<Integer>> typeToken = new TypeToken<ArrayList<Integer>>(){};
        ArrayList<Integer> gsonList = gson.fromJson(gsonString, typeToken);
        System.out.println(gsonList);
    }

    private static void arrayExamples() {
        // 数组
        String[] arrStr = {"a", "b", "c"};

        // 序列化
        String gsonString = gson.toJson(arrStr);
        System.out.println(gsonString);

        // 反序列化
        String[] gsonArr = gson.fromJson(gsonString, String[].class);
        System.out.println(Arrays.toString(gsonArr));
    }

    private static void objectExamples() {
        // Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // 对象类型序列化
        GsonObject gsonObject = new GsonObject("Elliot");
        gsonObject.setAddress("Ningbo");

        GsonJobObject gsonJobObject = new GsonJobObject();
        gsonJobObject.setJobName("jobName");
        gsonJobObject.setJobDesc("jobDesc");
        gsonObject.setGsonJobObject(gsonJobObject);

        // 序列化
        String gsonStr = gson.toJson(gsonObject);
        System.out.println(gsonStr);

        // 反序列化
        GsonObject gsonObjectFromJson = gson.fromJson(gsonStr, GsonObject.class);
        System.out.println(gsonObjectFromJson);

        // 进阶
        String str = "{\"id\":0,\"loginName\":\"Elliot\",\"gsonJobObject\":{\"jobName\":\"jobName\",\"jobDesc\":\"jobDesc\"}}";
        GsonObject gsonObjectFromJson2 = gson.fromJson(str, GsonObject.class);
        System.out.println(gsonObjectFromJson2);
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

/**
 * 基本类型有默认值
 * 包装类型无值不解析
 * transient 不参与序列化和反序列化
 */
class GsonObject extends GsonParentObject{
    private int id;
    private Integer age;
    @SerializedName("loginName")// 指定字段名解析
    private String name;
    @Expose // 指定序列化和反序列化
    private String address;
    private GsonJobObject gsonJobObject;

    GsonObject() {

    }

    public GsonObject(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GsonJobObject getGsonJobObject() {
        return gsonJobObject;
    }

    public void setGsonJobObject(GsonJobObject gsonJobObject) {
        this.gsonJobObject = gsonJobObject;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "GsonObject{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gsonJobObject=" + gsonJobObject +
                '}';
    }
}

class GsonParentObject {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

class GsonJobObject {

    private String jobName;
    private String jobDesc;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    @Override
    public String toString() {
        return "GsonJobObject{" +
                "jobName='" + jobName + '\'' +
                ", jobDesc='" + jobDesc + '\'' +
                '}';
    }
}
