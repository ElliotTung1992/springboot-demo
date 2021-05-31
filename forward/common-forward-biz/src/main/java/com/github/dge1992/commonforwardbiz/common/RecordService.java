package com.github.dge1992.commonforwardbiz.common;

import com.github.dge1992.commonforwardapi.model.CommonReceiveRequest;

import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-19 17:38
 */
public interface RecordService {

    /**
     * 添加记录
     * @param commonRemoteObj 远程访问对象
     * @author dge
     * @date 2021-01-19 17:39
     */
    void add(CommonReceiveRequest commonRemoteObj);

    /**
     * 查询记录列表
     * @return java.util.List<com.github.dge1992.commonforward.api.model.CommonReceiveObject>
     * @author dge
     * @date 2021-01-19 17:42
     */
    List<CommonReceiveRequest> queryListOrderByCreateTime();

    /**
     * 查询失败记录列表
     * @return java.util.List<com.github.dge1992.commonforward.api.model.CommonReceiveObject>
     * @author dge
     * @date 2021-01-19 17:44
     */
    List<CommonReceiveRequest> queryNotSuccessListOrderByCreateTime();

    /**
     * 根据uuid进行重试
     * @param uuid 唯一标识
     * @author dge
     * @date 2021-01-19 17:52
     */
    void retryByUuid(String uuid);

    /**
     * 幂等
     * @param uuid uuid 唯一标识
     * @return int
     * @author dge
     * @date 2021-01-20 09:51
     */
    int idempotent(String uuid);
}
