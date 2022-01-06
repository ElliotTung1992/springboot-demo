package com.github.dge.fishspringbootstarter.listeners;

import com.alibaba.fastjson.JSON;
import com.github.dge.core.config.ZkClient;
import com.github.dge.core.instance.ConfigCenterManager;
import com.github.dge.core.listeners.ZkConnectionStateChangeListener;
import com.github.dge.fishspringbootstarter.cache.ValueBeanDefinitionContainer;
import com.github.dge.fishspringbootstarter.constants.ConfigCenterConstant;
import com.github.dge.fishspringbootstarter.domain.AppConfigData;
import com.github.dge.fishspringbootstarter.instance.ConfigCenterInstance;
import com.github.dge.fishspringbootstarter.processor.ValueBeanPostProcessor;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * 监听ApplicationEnvironmentPreparedEvent事件
 * @author dge
 * @date 12/14/21 5:54 PM
 *
 * 1. 当项目环境变量加载成功执行之后拉取配置中心配置数据并放置到spring环境变量中{@link EnvironmentPreparedListener}
 * 2. 自定义Bean后置处理器{@link ValueBeanPostProcessor}获取使用{@link org.springframework.beans.factory.annotation.Value}
 *    注解的Bean对象并放置至缓存{@link ValueBeanDefinitionContainer}
 * 3. 监听{@link ConfigCenterZkNodeChangeListener}配置中心配置变更，刷新bean的属性值
 * 4. 监听zk链接状态{@link ZkConnectionStateChangeListener}
 */
public class EnvironmentPreparedListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        String configCenterEnable = environment.getProperty(ConfigCenterConstant.FISH_CONFIG_CENTER_ENABLE);
        if(!Boolean.TRUE.toString().equalsIgnoreCase(configCenterEnable)){
            return;
        }
        MutablePropertySources propertySources = environment.getPropertySources();
        // 获取环境变量，构建对象
        initConfigCenterInstance(environment);
        // 加载配置中心自定义配置
        AppConfigData appConfigData = getConfigCenterData();
        if(Objects.nonNull(appConfigData)){
            Map<String, String> configFileDataMap = appConfigData.getConfigFileDataMap();
            Properties properties = new Properties();
            configFileDataMap.forEach(properties::setProperty);
            PropertiesPropertySource source = new PropertiesPropertySource("configCenter", properties);
            propertySources.addLast(source);
        }
        // 设置路径监听
        setListener();
    }

    private void initConfigCenterInstance(ConfigurableEnvironment environment) {
        String appCode = environment.getProperty(ConfigCenterConstant.FISH_CONFIG_CENTER_APP_CODE);
        String serverUrl = environment.getProperty(ConfigCenterConstant.FISH_CONFIG_CENTER_SERVER_URL);
        String env = environment.getProperty(ConfigCenterConstant.FISH_CONFIG_CENTER_ENV);
        String appName = environment.getProperty(ConfigCenterConstant.FISH_CONFIG_CENTER_APP_NAME);
        String enable = environment.getProperty(ConfigCenterConstant.FISH_CONFIG_CENTER_ENABLE);
        ConfigCenterInstance instance = ConfigCenterInstance.getInstance();
        instance.setAppCode(appCode);
        instance.setServerUrl(serverUrl);
        instance.setEnv(env);
        instance.setAppName(appName);
        instance.setEnable(enable);
    }

    private AppConfigData getConfigCenterData(){
        ZkClient zkClient = ConfigCenterManager.getZkClient();
        String path = ConfigCenterInstance.getInstance().getPath();
        String nodeData = zkClient.getNodeData(path);
        if(StringUtils.isBlank(nodeData)){
            return null;
        }
        AppConfigData appConfigData = JSON.parseObject(nodeData, AppConfigData.class);
        return appConfigData;
    }

    private void setListener(){
        // 设置zk路径变更监听
        CuratorFramework curatorFramework = ConfigCenterManager.getCuratorFramework();
        String path = ConfigCenterInstance.getInstance().getPath();
        NodeCache nodeCache = new NodeCache(curatorFramework, path, false);
        ConfigCenterZkNodeChangeListener listener = new ConfigCenterZkNodeChangeListener(nodeCache);
        try {
            nodeCache.start(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        nodeCache.getListenable().addListener(listener);
        // 设置zk链接监听
        ZkConnectionStateChangeListener zkConnectionStateChangeListener = new ZkConnectionStateChangeListener();
        curatorFramework.getConnectionStateListenable().addListener(zkConnectionStateChangeListener);
    }
}
