package com.github.dge1992.commonforwardbiz.post;


import com.github.dge1992.commonforwardapi.model.CommonReceiveRequest;
import com.github.dge1992.commonforwardbiz.post.strategy.PostStrategy;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-13 16:13
 */
public class PostStrategyHandler {

    private PostStrategy postStrategy;

    public PostStrategyHandler(PostStrategy postStrategy) {
        this.postStrategy = postStrategy;
    }

    public void execute(CommonReceiveRequest commonReceiveObject, String resultStr) {
        postStrategy.postExecute(commonReceiveObject, resultStr);
    }
}
