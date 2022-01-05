package com.github.dge.core.listeners;

import com.github.dge.core.instance.ConfigCenterInstance;
import com.github.dge.core.instance.ConfigCenterManager;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 配置中心数据监听器
 * @author dge
 * @date 12/14/21 8:46 PM
 */
@Component
public class ConfigFileNodeDataChangedWatcher implements NodeCacheListener {

    @Override
    public void nodeChanged() throws Exception {
        CuratorFramework curatorFramework = ConfigCenterManager.getCuratorFramework();
        ConfigCenterInstance configurationCenterInstance = ConfigCenterInstance.getInstance();
        String path = configurationCenterInstance.getPath();
        String name = new String(curatorFramework.getData().forPath(path), StandardCharsets.UTF_8);
        System.out.println("数据放生变更了");
        System.out.println(name);
    }
}
