package com.github.dge1992.fish.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.alibaba.fastjson2.annotation.JSONField;
import com.github.dge1992.fish.constants.enums.GenderEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

public class FastJsonTest {

    public static void main(String[] args) {
        primitivesExamples();
        mapsExamples();
        arrayExamples();
        collectionsExamples();
        objectExamples();
    }

    private static void objectExamples() {
        FastjsonObject fastjsonObject = new FastjsonObject();
        fastjsonObject.setName("Elliot");
        fastjsonObject.setAge(31);
        fastjsonObject.setLocalDateTime(LocalDateTime.now());
        fastjsonObject.setDate(new Date());
        fastjsonObject.setGenderEnum(GenderEnum.MALE);

        List<FastjsonJobObject> fastjsonJobObjectList = new ArrayList<>();
        FastjsonJobObject fastjsonJobObject = new FastjsonJobObject();
        fastjsonJobObject.setJobName("name1");
        fastjsonJobObject.setJobDesc("desc1");
        fastjsonJobObjectList.add(fastjsonJobObject);
        FastjsonJobObject fastjsonJobObject2 = new FastjsonJobObject();
        fastjsonJobObject2.setJobName("name2");
        fastjsonJobObject2.setJobDesc("desc2");
        fastjsonJobObjectList.add(fastjsonJobObject2);
        fastjsonObject.setFastjsonJobObjectList(fastjsonJobObjectList);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Elliot");
        map.put("age", 23);
        fastjsonObject.setMap(map);

        String jsonString = JSON.toJSONString(fastjsonObject);
        System.out.println(jsonString);

        jsonString = "{\"age1\":31,\"date\":\"2024-01-11 19:28:33.177\",\"fastjsonJobObjectList\":[{\"jobDesc\":\"desc1\",\"jobName\":\"name1\"},{\"jobDesc\":\"desc2\",\"jobName\":\"name2\"}],\"genderEnum\":1,\"id\":0,\"localDateTime\":\"2024-01-11 19:28:33\",\"map\":{\"name\":\"Elliot\",\"age\":23},\"name\":\"Elliot\"}";

        FastjsonObject jsonObject = JSON.parseObject(jsonString, FastjsonObject.class);
        System.out.println(jsonObject);
    }

    private static void collectionsExamples() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        String jsonString = JSON.toJSONString(list);
        System.out.println(jsonString);

        List<Integer> jsonList = JSON.parseArray(jsonString, Integer.class);
        System.out.println(jsonList);
    }

    private static void arrayExamples() {
        String[] names = {"Elliot", "Mike", "Jack"};
        String jsonString = JSON.toJSONString(names);
        System.out.println(jsonString);
        String[] strings = JSON.parseObject(jsonString, String[].class);
        System.out.println(Arrays.toString(strings));
    }

    private static void mapsExamples() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Elliot");
        map.put("address", "Nongbo");

        String jsonString = JSON.toJSONString(map);
        System.out.println(jsonString);

        TypeReference<Map<String, String>> typeReference = new TypeReference<Map<String, String>>() {};
        Map<String, String> jsonMap = JSON.parseObject(jsonString, typeReference);
        System.out.println(jsonMap);
    }

    private static void primitivesExamples() {
        // 基本数据类型
        System.out.println(JSON.toJSONString(1));
        System.out.println(JSON.parseObject("1", Integer.class));

        System.out.println(JSON.toJSONString(Long.valueOf("11")));
        System.out.println(JSON.parseObject("11", Long.class));

        System.out.println(JSON.toJSONString(Boolean.TRUE));
        System.out.println(JSON.parseObject("true", Boolean.class));

        System.out.println(JSON.toJSONString("Elliot"));
    }
}

/**
 * 基本类型有默认值, 包装类型无值不解析
 * @JSONField 实现序列化别名及是否序列化和反序列化
 * @JSONField 可设置序列化时间格式
 */
@Data
class FastjsonObject extends FastjsonParentObject {
    private int id;
    @JSONField(serialize = true, name = "age1")
    private Integer age;
    private String name;
    private String address;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;
    private Date date;
    private List<FastjsonJobObject> fastjsonJobObjectList;
    private Map<String, Object> map;
    @JSONField(value = true)
    private GenderEnum genderEnum;
}
@Data
class FastjsonJobObject {
    private String jobName;
    private String jobDesc;
}
@Data
class FastjsonParentObject {
    private String name;
}


