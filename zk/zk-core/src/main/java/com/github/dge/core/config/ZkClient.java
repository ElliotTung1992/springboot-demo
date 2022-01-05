package com.github.dge.core.config;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-13 11:25 AM
 */
@Validated
public class ZkClient {

    private CuratorFramework curatorFramework;

    public ZkClient(CuratorFramework curatorFramework){
        this.curatorFramework = curatorFramework;
    }

    /**
     * 开启zk连接状态变更监听器
     *
     * @param connectionStateListener connectionStateListener
     * @author dge
     * @date 12/13/21 2:42 PM
     */
    public void startConnectionStateListener(@NotNull(message = "Zk链接状态监听器不能为空") ConnectionStateListener
                                                     connectionStateListener) {
        curatorFramework.getConnectionStateListenable().addListener(connectionStateListener);
    }

    /**
     * 创建zk结点
     *
     * @param path 路径
     * @param data 数据
     * @param createMode zk结点类型
     * @author dge
     * @date 12/13/21 2:51 PM
     */
    public void createNode(@NotBlank(message = "zk路径不能为空")String path, String data,
                           @NotNull(message = "zk结点类型不能为空") CreateMode createMode){
        try {
            curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(createMode)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path, StringUtils.isBlank(data) ? null : data.getBytes(Charset.forName("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断结点是否存在
     *
     * @param path 路径
     * @return java.lang.Boolean
     * @author dge
     * @date 12/13/21 2:58 PM
     */
    public Boolean isNodeExist(@NotBlank(message = "zk路径不能为空")String path){
        try {
            return curatorFramework.checkExists().forPath(path) != null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("zNode is not exist for path:" + path);
        }
    }

    /**
     * 判断是否包含子路径
     *
     * @param path path
     * @return java.lang.Boolean
     * @author dge
     * @date 12/13/21 9:11 PM
     */
    public Boolean hasChildren(@NotBlank(message = "zk路径不能为空")String path){
        return CollectionUtils.isNotEmpty(getChildrenNode(path));
    }

    /**
     * 删除结点
     *
     * @param path 路径
     * @author dge
     * @date 12/13/21 3:03 PM
     */
    public void deleteNode(@NotBlank(message = "zk路径不能为空")String path) {
        if(!isNodeExist(path)){
            return;
        }
        if(hasChildren(path)){
            throw new RuntimeException("path:" + path + " has children can not delete");
        }
        try {
            Stat stat = new Stat();
            curatorFramework.getData().storingStatIn(stat).forPath(path);
            curatorFramework.delete().guaranteed().withVersion(stat.getVersion()).forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("delete zNode fail for path:" + path);
        }
    }

    /**
     * 删除结点及子结点
     *
     * @param path 路径
     * @author dge
     * @date 12/13/21 3:06 PM
     */
    public void deletingChildrenIfNeeded(@NotBlank(message = "zk路径不能为空")String path) {
        try {
            if(!isNodeExist(path)){
                return;
            }
            Stat stat = new Stat();
            curatorFramework.getData().storingStatIn(stat).forPath(path);
            curatorFramework.delete().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("deletingChildrenIfNeeded zNode fail for path:" + path);
        }
    }

    /**
     * 获取结点数据
     *
     * @param path 路径
     * @return java.lang.String
     * @author dge
     * @date 12/13/21 3:11 PM
     */
    public String getNodeData(@NotBlank(message = "zk路径不能为空")String path) {
        if(!isNodeExist(path)){
            throw new RuntimeException("path:" + path + " is not exist");
        }
        try {
            byte[] data = curatorFramework.getData().forPath(path);
            if (data != null) {
                return new String(data, Charset.forName("UTF-8"));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("get mode data fail for path:" + path);
        }
    }

    /**
     * 获取子结点数据
     *
     * @param path 路径
     * @return java.util.List<java.lang.String>
     * @author dge
     * @date 12/13/21 3:13 PM
     */
    public List<String> getChildrenNode(@NotBlank(message = "zk路径不能为空")String path) {
        if(!isNodeExist(path)){
            throw new RuntimeException("path:" + path + " is not exist");
        }
        try {
            return curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("get children node data fail for path:" + path);
        }
    }

    /**
     * 更新结点数据
     *
     * @param path 路径
     * @param data 数据
     * @author dge
     * @date 12/13/21 3:42 PM
     */
    public void updateNodeData(@NotBlank(message = "zk路径不能为空")String path, String data) {
        if(!isNodeExist(path)){
            throw new RuntimeException("path:" + path + " is not exist");
        }
        try {
            Stat stat = new Stat();
            curatorFramework.getData().storingStatIn(stat).forPath(path);
            curatorFramework.setData().withVersion(stat.getVersion()).forPath(path, StringUtils.isBlank(data) ? null :
                    data.getBytes(Charset.forName("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("update zNode data error fro path:" + path + " data:" + data);
        }
    }

    /**
     * 添加监听器
     *
     * @param nodeCache nodeCache
     * @param nodeCacheListener nodeCacheListener
     * @author dge
     * @date 12/13/21 3:45 PM
     */
    public void addNodeCacheListener(@NotNull(message = "被监听结点不能为空") NodeCache nodeCache,
                                     @NotNull(message = "结点监听器不能为空") NodeCacheListener nodeCacheListener) {
        try {
            nodeCache.start(true);
            nodeCache.getListenable().addListener(nodeCacheListener);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("add nodeCache listener error");
        }
    }

    /**
     * 构建一个NodeCache
     *
     * @param path path
     * @return org.apache.curator.framework.recipes.cache.NodeCache
     * @author dge
     * @date 12/13/21 3:46 PM
     */
    public NodeCache buildNodeCache(@NotBlank(message = "zk路径不能为空")String path) {
        return new NodeCache(curatorFramework, path, false);
    }

    /**
     * 给子结点添加监听
     *
     * @param path path
     * @param pathChildrenCacheListener pathChildrenCacheListener
     * @author dge
     * @date 12/13/21 3:49 PM
     */
    public void addPathChildrenCacheListener(@NotBlank(message = "zk路径不能为空")String path, PathChildrenCacheListener pathChildrenCacheListener) {
        try {
            PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
            pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
            pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("add path children CacheListener error");
        }
    }
}
