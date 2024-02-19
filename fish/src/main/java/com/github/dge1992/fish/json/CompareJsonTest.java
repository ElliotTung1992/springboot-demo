package com.github.dge1992.fish.json;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dge1992.fish.domain.po.PeoplePo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompareJsonTest {

    /**
     * 结论:
     * FastJson和Jackson把对象序列化成json字符串的时候, 是通过反射获取到对象所有的的getXxx()方法, Xxx默认为对象的属性
     * Gson把对象序列化成json字符串时, 是通过反射获取到多想的所有属性
     */
    public static void main(String[] args) throws JsonProcessingException {
        PeoplePo people = new PeoplePo();
        people.setMale(Boolean.TRUE);

        String fastJson = JSON.toJSONString(people);
        log.info("fastJson:{}", fastJson);

        Gson gson = new Gson();
        String gsonStr = gson.toJson(people);
        log.info("Gson:{}", gsonStr);

        ObjectMapper objectMapper = new ObjectMapper();
        String jackson = objectMapper.writeValueAsString(people);
        log.info("jackson:{}", jackson);
    }
}
