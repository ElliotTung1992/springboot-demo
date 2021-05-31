package com.github.dge1992.commonforwardbiz.template;

import com.alibaba.fastjson.JSON;
import com.github.dge1992.commonforwardapi.model.result.BaseResult;
import com.github.dge1992.commonforwardapi.model.CommonReceiveRequest;
import com.github.dge1992.commonforwardbiz.common.RecordService;
import com.github.rholder.retry.Retryer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 董感恩
 * @date 2020-07-02 09:38
 * 远程访问模版
 */
@Component
public abstract class BaseForwardTemplate<T extends BaseResult> {

    private Logger logger = LoggerFactory.getLogger(BaseForwardTemplate.class);

    @Autowired
    private RecordService recordService;

    @Autowired
    private Retryer retryer;

    /**
     * 前置处理
     * @param receiveObject 接收对象
     * @author dge
     * @date 2021-01-19 11:13
     */
    protected abstract void preExecute(CommonReceiveRequest receiveObject);

    /**
     * 发送
     * @param receiveObject 接收对象
     * @return com.github.dge1992.commonforward.api.model.BaseResult
     * @author dge
     * @date 2021-01-19 11:13
     */
    protected abstract T send(CommonReceiveRequest receiveObject) throws Exception;

    /**
     * 后置处理
     * @param t        结果
     * @param receiveObject 接收对象
     * @author dge
     * @date 2021-01-19 11:14
     */
    protected abstract void postExecute(T t, CommonReceiveRequest receiveObject);

    /**
     * 远程访问
     * @param commonRemoteObj 接收对象
     * @author dge
     * @date 2021-01-19 11:14
     */
    public final T forward(CommonReceiveRequest commonRemoteObj) {
        logger.info("BaseForwardTemplate | forward | commonRemoteObj is :{}" + JSON.toJSONString(commonRemoteObj));
        // todo 此方法需要加锁
        try {
            validate(commonRemoteObj);
            //幂等
            if (recordService.idempotent(commonRemoteObj.getUuid()) == 0) {
                T t = template(commonRemoteObj);
                commonRemoteObj.setIsSuccess(Boolean.TRUE);
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonRemoteObj.setIsSuccess(Boolean.FALSE);
        } finally {
            //数据记录
            recordService.add(commonRemoteObj);
        }
        return null;
    }

    /**
     * 模版方法
     * @param commonRemoteObj 接收对象
     * @author dge
     * @date 2021-01-20 09:54
     */
    public final T template(CommonReceiveRequest commonRemoteObj) throws Exception {
        preExecute(commonRemoteObj);
        T result = (T) retryer.call(() -> send(commonRemoteObj));
        postExecute(result, commonRemoteObj);
        return result;
    }

    /**
     * 公共字段的校验
     * @param commonRemoteObj 公共接收对象
     * @author dge
     * @date 2021-01-19 13:55
     */
    private void validate(CommonReceiveRequest commonRemoteObj) {
        //请求方法
        Integer method = commonRemoteObj.getMethod();
        //url
        String url = commonRemoteObj.getURL();
        //url编号
        String urlCode = commonRemoteObj.getURLCode();
        //唯一标识
        String uuid = commonRemoteObj.getUuid();
        if (method == null) {
            throw new NullPointerException("method is null");
        }
        if (StringUtils.isBlank(url) && StringUtils.isBlank(urlCode)) {
            throw new NullPointerException("url and urlCode all null");
        }
        if (StringUtils.isBlank(uuid)) {
            throw new NullPointerException("uuid is null");
        }
    }

}
