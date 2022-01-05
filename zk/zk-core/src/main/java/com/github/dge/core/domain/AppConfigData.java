package com.github.dge.core.domain;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-27 8:32 PM
 */
public class AppConfigData {

    /**
     * 应用名称
     */
    private String appName;
    /**
     * 配置字段数据map
     */
    private Map<String, String> configFileDataMap;
    /**
     * 版本号
     */
    private String version;

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Map<String, String> getConfigFileDataMap() {
        return this.configFileDataMap;
    }

    public void setConfigFileDataMap(Map<String, String> configFileDataMap) {
        this.configFileDataMap = configFileDataMap;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static void main(String[] args) {
        AppConfigData appConfigData = new AppConfigData();
        appConfigData.setAppName("sample");
        Map<String, String> map = new HashMap<>();
        map.put("name", "dge");
        appConfigData.setConfigFileDataMap(map);
        appConfigData.setVersion("aaa");

        String s = JSON.toJSONString(appConfigData);
        System.out.println(s);
    }
}
