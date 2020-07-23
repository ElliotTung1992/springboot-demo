package com.github.dge1992.restfuldemo.service;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

/**
 * @author 董感恩
 * @date 2020-07-23 17:21
 * @desc
 */
public interface IAsyncService {

    @Async
    void async();

    @Async
    Future<String> asyncHasResult(int i);
}
