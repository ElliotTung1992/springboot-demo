package com.github.dge.core.instance;

import com.github.dge.core.constants.ConfigCenterConstant;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-14 9:06 PM
 */
public class ConfigCenterInstance {

    /**
     * 系统编码
     */
    private String appCode;

    /**
     * 服务地址
     */
    private String serverUrl;

    /**
     * 环境选择
     */
    private String env;

    /**
     * 系统名称
     */
    private String appName;

    /**
     * 是否开启配置中心
     */
    private String enable;

    private ConfigCenterInstance(){

    }

    public static ConfigCenterInstance getInstance(){
        return InnerClass.INSTANCE;
    }

    public static class InnerClass{
        private static ConfigCenterInstance INSTANCE = new ConfigCenterInstance();
    }

    public String getPath(){
        return ConfigCenterConstant.ZOOKEEPER_CONFIG_CENTER_ROOT.concat(ConfigCenterConstant.SLASH).concat(appCode)
                .concat(ConfigCenterConstant.SLASH).concat(env);
    }

    public String getAppCode() {
        return this.appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getServerUrl() {
        return this.serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getEnv() {
        return this.env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getEnable() {
        return this.enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
