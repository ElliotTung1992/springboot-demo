package com.github.dge1992.commonforward.lib.middleware.httpclient;

import org.apache.http.conn.HttpClientConnectionManager;

/**
 * @author 董感恩
 * @date 2020-02-24 17:30
 * @desc 连接逐出策略
 */
public class IdleConnectionThread extends Thread {

    private HttpClientConnectionManager connMgr;
    private volatile boolean shutdown;

    public IdleConnectionThread(HttpClientConnectionManager connMgr) {
        super();
        this.connMgr = connMgr;
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    // 关闭失效的连接
                    connMgr.closeExpiredConnections();
                }
            }
        } catch (InterruptedException ex) {
            // 结束
        }
    }

    //关闭清理无效连接的线程
    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }

}
