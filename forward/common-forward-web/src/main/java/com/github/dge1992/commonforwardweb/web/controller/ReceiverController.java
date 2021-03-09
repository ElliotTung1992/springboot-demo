package com.github.dge1992.commonforwardweb.web.controller;

import com.github.dge1992.commonforwardapi.model.CommonReceiveObject;
import com.github.dge1992.commonforwardbiz.template.BaseForwardTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-04 22:46
 * Http远程调用控制器
 */
@RestController
public class ReceiverController {

    @Autowired
    private BaseForwardTemplate baseForwardTemplate;

    @PostMapping("/receive")
    public void receive(@RequestBody CommonReceiveObject receiveObject) {
        CompletableFuture.runAsync(() -> baseForwardTemplate.forward(receiveObject))
                .exceptionally(e -> {
                    // 异常处理
                    e.printStackTrace();
                    return null;
                });
    }
}
