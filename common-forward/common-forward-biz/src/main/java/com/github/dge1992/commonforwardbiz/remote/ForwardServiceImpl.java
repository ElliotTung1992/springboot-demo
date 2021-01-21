package com.github.dge1992.commonforwardbiz.remote;

import com.github.dge1992.commonforwardapi.model.CommonReceiveObject;
import com.github.dge1992.commonforwardapi.remote.ForwardService;
import com.github.dge1992.commonforwardbiz.template.BaseForwardTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-21 17:48
 */
@Service
public class ForwardServiceImpl implements ForwardService {

    @Autowired
    private BaseForwardTemplate baseForwardTemplate;

    @Override
    public void receive(CommonReceiveObject receiveObject) {
        baseForwardTemplate.forward(receiveObject);
    }
}
