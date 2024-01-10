package com.github.dge1992.fish.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

public class JacksonTest {

    public static void main(String[] args) throws Exception {
        objectExamples();
        mapsExamples();
        collectionsExamples();
        arrayExamples();
    }

    private static void arrayExamples() throws Exception {
        String[] arr = {"Mike", "Jack"};

        ObjectMapper objectMapper = new ObjectMapper();
        // 序列化
        String jsonString = objectMapper.writeValueAsString(arr);
        System.out.println(jsonString);
        // 反序列化
        String[] jsonArr = objectMapper.readValue(jsonString, String[].class);
        System.out.println(Arrays.toString(jsonArr));

    }

    private static void collectionsExamples() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        ObjectMapper objectMapper = new ObjectMapper();

        // 序列化
        String jsonString = objectMapper.writeValueAsString(list);
        System.out.println(jsonString);

        // 反序列化
        List<Integer> jsonList = objectMapper.readValue(jsonString, new TypeReference<List<Integer>>() {
        });
        System.out.println(jsonList);
    }

    private static void mapsExamples() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Elliot");
        map.put("address", "Ningbo");

        ObjectMapper objectMapper = new ObjectMapper();
        // 序列化
        String jsonString = objectMapper.writeValueAsString(map);
        System.out.println(jsonString);

        // 反序列化
        Map<String, String> jsonMap =
                objectMapper.readValue(jsonString, new TypeReference<Map<String, String>>() {});
        System.out.println(jsonMap);
    }

    private static void objectExamples() throws Exception {
        JacksonObject jacksonObject = new JacksonObject();
        jacksonObject.setAge(31);
        jacksonObject.setName("Elliot");
        jacksonObject.setAddress("Ningbo");
        jacksonObject.setBirthday(LocalDateTime.now());
        jacksonObject.setDate(new Date());

        List<JacksonJobObject> jacksonJobObjectList = new ArrayList<>();
        JacksonJobObject jacksonJobObject1 = new JacksonJobObject();
        jacksonJobObject1.setJobName("jobname1");
        jacksonJobObject1.setJobDesc("jobdesc1");
        jacksonJobObjectList.add(jacksonJobObject1);

        JacksonJobObject jacksonJobObject2 = new JacksonJobObject();
        jacksonJobObject2.setJobName("jobname2");
        jacksonJobObject2.setJobDesc("jobdesc2");
        jacksonJobObjectList.add(jacksonJobObject2);
        jacksonObject.setJacksonJobObjectList(jacksonJobObjectList);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Elliot");
        map.put("age", 24);
        jacksonObject.setMap(map);

        // 序列化
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String stringJson = objectMapper.writeValueAsString(jacksonObject);
        System.out.println(stringJson);

        // 反序列化
        JacksonObject jsonObject = objectMapper.readValue(stringJson, JacksonObject.class);
        System.out.println(jsonObject);

    }


}

/**
 * 基本数据类型无值会设置初始值, 包装类型无值不处理
 * 序列化默认时间格式设置为时间戳
 * @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")序列化时间
 * @JsonProperty(access = JsonProperty.Access.READ_ONLY) 控制字段序列化和反序列化
 * @JsonIgnore 忽略字段
 */
@Data
class JacksonObject extends JacksonParentObject {
    private int id;
    private Integer age;
    @JsonProperty(value = "loginName", access = JsonProperty.Access.READ_WRITE)
    private String name;
    @JsonIgnore
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private List<JacksonJobObject> jacksonJobObjectList;
    private Map<String, Object> map;
}
@Data
class JacksonJobObject {
    private String jobName;
    private String jobDesc;
}
@Data
class JacksonParentObject {
    private String address;
}
