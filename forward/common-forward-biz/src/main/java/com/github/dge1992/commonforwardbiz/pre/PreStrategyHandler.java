package com.github.dge1992.commonforwardbiz.pre;


import com.github.dge1992.commonforwardapi.model.CommonReceiveRequest;
import com.github.dge1992.commonforwardbiz.pre.strategy.PreStrategy;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-13 16:13
 */
public class PreStrategyHandler {

    private PreStrategy preStrategy;

    public PreStrategyHandler(PreStrategy preStrategy) {
        this.preStrategy = preStrategy;
    }

    public void execute(CommonReceiveRequest receiveObject) {
        preStrategy.preExecute(receiveObject);
    }
}
