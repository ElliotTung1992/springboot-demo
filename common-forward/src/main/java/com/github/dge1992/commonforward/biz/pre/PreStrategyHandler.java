package com.github.dge1992.commonforward.biz.pre;

import com.github.dge1992.commonforward.api.model.CommonReceiveObject;
import com.github.dge1992.commonforward.biz.pre.strategy.PreStrategy;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-13 16:13
 */
public class PreStrategyHandler {

    private PreStrategy preStrategy;

    public PreStrategyHandler(PreStrategy preStrategy){
        this.preStrategy = preStrategy;
    }

    public void execute(CommonReceiveObject receiveObject){
        preStrategy.preExecute(receiveObject);
    }
}
