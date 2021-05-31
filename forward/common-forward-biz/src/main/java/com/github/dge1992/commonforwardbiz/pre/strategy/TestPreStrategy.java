package com.github.dge1992.commonforwardbiz.pre.strategy;

import com.github.dge1992.commonforwardapi.model.CommonReceiveRequest;
import org.springframework.stereotype.Component;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-13 16:11
 */
@Component("testPreStrategy")
public class TestPreStrategy implements PreStrategy {

    @Override
    public void preExecute(CommonReceiveRequest receiveObject) {
        System.out.println("HAHA");
    }
}
