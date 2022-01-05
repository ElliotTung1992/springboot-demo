package com.github.dge.core.config;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.zookeeper.CreateMode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-13 4:04 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ZkClientTest {

    @Autowired
    private ZkClient zkClient;

    @Test
    void startConnectionStateListener() {
        zkClient.startConnectionStateListener(null);
    }

    @Test
    void createNode() {
        zkClient.createNode("/test2", "123", CreateMode.EPHEMERAL);
    }

    @Test
    void isNodeExist() {
        System.out.println(zkClient.isNodeExist("/test"));
    }

    @Test
    void deleteNode() {
        zkClient.deleteNode("/test");
    }

    @Test
    void deletingChildrenIfNeeded() {
        zkClient.deletingChildrenIfNeeded("/test");
    }

    @Test
    void getNodeData() {
        String nodeData = zkClient.getNodeData("/test");
        System.out.println(nodeData);
    }

    @Test
    void getChildrenNode() {
        List<String> childrenNodeData = zkClient.getChildrenNode("/zk1");
        CollectionUtils.emptyIfNull(childrenNodeData).stream()
                .forEach(System.out::println);
    }

    @Test
    void updateNodeData() {
        zkClient.updateNodeData("/test", "hello zk");
    }

    @Test
    void addNodeCacheListener() {

    }

    @Test
    void buildNodeCache() {

    }

    @Test
    void addPathChildrenCacheListener() {

    }
}