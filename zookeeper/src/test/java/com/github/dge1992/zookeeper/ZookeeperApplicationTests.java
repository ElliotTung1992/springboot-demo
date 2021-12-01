package com.github.dge1992.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperApplicationTests {

    @Autowired
    private CuratorFramework zkclient;

    @Test
    public void contextLoads() {
    }

    @Test
    public void createNote() throws Exception {
        String path = "/test/test1";
        String value = "默认创建持久结点";
        String message = zkclient.create()
                .creatingParentsIfNeeded() //如果path的父目录不存在，则同时穿件父目录
                .forPath(path, value.getBytes(Charset.forName("UTF-8")));
        System.out.println(message);
    }

    @Test
    public void createEphemeralNote() throws Exception {
        String path = "/ephemeralTest";
        String value = "测试临时结点";
        String message = zkclient.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, value.getBytes(Charset.forName("UTF-8")));
        System.out.println(message);
        TimeUnit.SECONDS.sleep(100);
    }

    @Test
    public void createPersistentSequentialNote() throws Exception {
        String path = "/ephemeralTest";
        String value = "create persistent sequential Note";
        String message = zkclient.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                .forPath(path, value.getBytes(Charset.forName("UTF-8")));
        System.out.println(message);
    }

    @Test
    public void setData() throws Exception {
        String path = "/test";
        String value = "create persistent note";
        Stat stat = zkclient.setData().forPath(path, value.getBytes(Charset.forName("UTF-8")));
        System.out.println(stat);
    }

    @Test
    public void delete() throws Exception {
        String path = "/ephemeralTest0000000014";
        zkclient.delete().forPath(path);
    }

    @Test
    public void getData() throws Exception {
        String path = "/test";
        System.out.println(new String(zkclient.getData().forPath(path), Charset.forName("UTF-8")));
    }

    @Test
    public void getChildrenKeys() throws Exception {
        String path = "/test";
        List<String> result = zkclient.getChildren().forPath(path);
        for (String s : result) {
            System.out.println(s);
        }
    }

    @Test
    public void addNodeListener() throws Exception {
        String path = "/test";
        NodeCache nodeCache = new NodeCache(zkclient, path);
        nodeCache.getListenable().addListener(() -> {
            System.out.println("触发监听了");
            System.out.println(zkclient.getData().forPath(path));
        });
        nodeCache.start();

        System.in.read();
    }
}
