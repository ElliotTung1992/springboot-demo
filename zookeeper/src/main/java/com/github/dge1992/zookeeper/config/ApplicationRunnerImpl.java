package com.github.dge1992.zookeeper.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-08 10:11 AM
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Autowired
    private CuratorFramework zkclient;

    @Autowired
    private SubConfiguration subConfiguration;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("监听器被执行");
        String path = "/zk/config/name";
        NodeCache nodeCache = new NodeCache(zkclient, path);
        nodeCache.getListenable().addListener(() -> {
            String name = new String(zkclient.getData().forPath(path), Charset.forName("UTF-8"));
            System.out.println("数据放生变更了");
            subConfiguration.setName(name);
        });
        nodeCache.start();
    }
}
