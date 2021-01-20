package com.github.dge1992.commonforward.biz.post.strategy;

import com.github.dge1992.commonforward.api.model.CommonReceiveObject;
import org.springframework.stereotype.Component;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-13 16:11
 */
@Component("testPostStrategy")
public class TestPostStrategy implements PostStrategy{

    @Override
    public void postExecute(CommonReceiveObject commonReceiveObject, String resultStr) {
        System.out.println("HEHE");
    }
}
