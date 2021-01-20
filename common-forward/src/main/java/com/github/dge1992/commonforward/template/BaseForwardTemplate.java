package com.github.dge1992.commonforward.template;

import com.alibaba.fastjson.JSON;
import com.github.dge1992.commonforward.api.model.BaseResult;
import com.github.dge1992.commonforward.api.model.CommonReceiveObject;
import com.github.dge1992.commonforward.biz.common.RecordService;
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
public abstract class BaseForwardTemplate {

    private Logger logger = LoggerFactory.getLogger(BaseForwardTemplate.class);

    @Autowired
    private RecordService recordService;

    @Autowired
    private Retryer retryer;

    /**
     * 前置处理
     *
     * @param receiveObject 接收对象
     * @author dge
     * @date 2021-01-19 11:13
     */
    protected abstract void preExecute(CommonReceiveObject receiveObject);

    /**
     * 发送
     *
     * @param receiveObject 接收对象
     * @return com.github.dge1992.commonforward.api.model.BaseResult
     * @author dge
     * @date 2021-01-19 11:13
     */
    protected abstract BaseResult send(CommonReceiveObject receiveObject) throws Exception;

    /**
     * 后置处理
     *
     * @param result        结果
     * @param receiveObject 接收对象
     * @author dge
     * @date 2021-01-19 11:14
     */
    protected abstract void postExecute(BaseResult result, CommonReceiveObject receiveObject);

    /**
     * 远程访问
     *
     * @param commonRemoteObj 接收对象
     * @author dge
     * @date 2021-01-19 11:14
     */
    public final void forward(CommonReceiveObject commonRemoteObj) {
        // todo 此方法需要加锁
        try {
            logger.info("BaseForwardTemplate forward commonRemoteObj is :" + JSON.toJSONString(commonRemoteObj));
            validate(commonRemoteObj);
            //幂等
            int idempotent = recordService.idempotent(commonRemoteObj.getUuid());
            if (idempotent == 0) {
                template(commonRemoteObj);
                commonRemoteObj.setIsSuccess(Boolean.TRUE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonRemoteObj.setIsSuccess(Boolean.FALSE);
        } finally {
            //数据记录
            recordService.add(commonRemoteObj);
        }
    }

    /**
     * 模版方法
     *
     * @param commonRemoteObj 接收对象
     * @author dge
     * @date 2021-01-20 09:54
     */
    public final void template(CommonReceiveObject commonRemoteObj) throws Exception {
        preExecute(commonRemoteObj);
        Object obj = retryer.call(() -> send(commonRemoteObj));
        if (obj instanceof BaseResult) {
            BaseResult result = (BaseResult) obj;
            postExecute(result, commonRemoteObj);
        }
    }

    /**
     * 公共字段的校验
     *
     * @param commonRemoteObj 公共接收对象
     * @author dge
     * @date 2021-01-19 13:55
     */
    private void validate(CommonReceiveObject commonRemoteObj) {
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
