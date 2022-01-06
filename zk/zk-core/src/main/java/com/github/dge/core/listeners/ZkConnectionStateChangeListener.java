package com.github.dge.core.listeners;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-13 2:06 PM
 */
public class ZkConnectionStateChangeListener implements ConnectionStateListener {

    @Override
    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
        if(ConnectionState.LOST == connectionState){
            // TODO 当zk失去链接时 通知管理员处理
            try {
                while (Boolean.TRUE){
                    if (curatorFramework.getZookeeperClient().blockUntilConnectedOrTimedOut()){
                        // TODO 当zk重新链接时 通知管理员处理
                        System.out.println("zk reconnect");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
