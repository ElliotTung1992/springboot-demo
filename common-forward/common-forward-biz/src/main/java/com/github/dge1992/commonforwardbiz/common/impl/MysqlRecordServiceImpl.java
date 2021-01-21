package com.github.dge1992.commonforwardbiz.common.impl;

import com.github.dge1992.commonforwardapi.model.CommonReceiveObject;
import com.github.dge1992.commonforwardbiz.common.RecordService;
import com.github.dge1992.commonforwardbiz.template.BaseForwardTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-19 17:40
 */
@Service
public class MysqlRecordServiceImpl implements RecordService {

    @Autowired
    private BaseForwardTemplate baseForwardTemplate;

    @Override
    public void add(CommonReceiveObject commonRemoteObj) {

    }

    @Override
    public List<CommonReceiveObject> queryListOrderByCreateTime() {
        return null;
    }

    @Override
    public List<CommonReceiveObject> queryNotSuccessListOrderByCreateTime() {
        return null;
    }

    @Override
    public void retryByUuid(String uuid) {
        CommonReceiveObject commonRemoteObj = new CommonReceiveObject();
        baseForwardTemplate.forward(commonRemoteObj);
    }

    @Override
    public int idempotent(String uuid) {
        return 0;
    }
}
