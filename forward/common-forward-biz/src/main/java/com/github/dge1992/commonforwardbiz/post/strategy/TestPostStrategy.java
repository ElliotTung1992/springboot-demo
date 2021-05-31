package com.github.dge1992.commonforwardbiz.post.strategy;

import com.github.dge1992.commonforwardapi.model.CommonReceiveRequest;
import org.springframework.stereotype.Component;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-13 16:11
 */
@Component("testPostStrategy")
public class TestPostStrategy implements PostStrategy {

    @Override
    public void postExecute(CommonReceiveRequest commonReceiveObject, String resultStr) {
        System.out.println("HEHE");
    }
}
