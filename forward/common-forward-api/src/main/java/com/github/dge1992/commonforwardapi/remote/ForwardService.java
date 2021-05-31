package com.github.dge1992.commonforwardapi.remote;

import com.github.dge1992.commonforwardapi.model.CommonReceiveRequest;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-21 17:44
 */
public interface ForwardService {

    /**
     * Dubbo调用远程服务接口
     * @param receiveObject 远程接收对象
     * @author dge
     * @date 2021-01-21 17:45
     */
    void receive(CommonReceiveRequest receiveObject);
}
