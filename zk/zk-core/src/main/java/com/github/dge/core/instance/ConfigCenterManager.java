package com.github.dge.core.instance;

import com.github.dge.core.config.WrapperZk;
import com.github.dge.core.config.ZkClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.BeanFactory;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-31 4:41 PM
 */
public class ConfigCenterManager {

    private static ZkClient zkClient;

    private static CuratorFramework curatorFramework;

    private static BeanFactory beanFactory = null;

    public static synchronized ZkClient getZkClient(){
        if(zkClient == null){
            zkClient = new ZkClient(getCuratorFramework());
        }
        return zkClient;
    }

    public static synchronized CuratorFramework getCuratorFramework(){
        if(curatorFramework == null){
            WrapperZk wrapperZk = new WrapperZk();
            wrapperZk.setConnectString("47.100.220.23:2181");
            wrapperZk.setElapsedTimeMs(5000);
            wrapperZk.setConnectionTimeoutMs(5000);
            wrapperZk.setRetryCount(5);
            wrapperZk.setSessionTimeoutMs(60000);
            curatorFramework = CuratorFrameworkFactory.newClient(
                    wrapperZk.getConnectString(),
                    wrapperZk.getSessionTimeoutMs(),
                    wrapperZk.getConnectionTimeoutMs(),
                    new RetryNTimes(wrapperZk.getRetryCount(), wrapperZk.getElapsedTimeMs()));
            curatorFramework.start();
        }
        return curatorFramework;
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public static void setBeanFactory(BeanFactory beanFactory) {
        ConfigCenterManager.beanFactory = beanFactory;
    }
}
