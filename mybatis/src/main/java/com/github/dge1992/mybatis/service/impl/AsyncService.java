package com.github.dge1992.mybatis.service.impl;

import com.github.dge1992.mybatis.domain.User;
import com.github.dge1992.mybatis.mapper.UserMapper;
import com.github.dge1992.mybatis.service.IAsyncService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Future;

/**
 * @author 董感恩
 * @date 2020-07-23 16:58
 * @desc
 */
@Service
public class AsyncService implements IAsyncService {

    @Autowired
    private UserMapper userMapper;

    @Async
    public void async(){
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("呵呵");
    }

    @Async
    public Future<String> asyncHasResult(int i){
        try {
            // 这个方法需要调用500毫秒
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 消息汇总
        return new AsyncResult<>(String.format("这个是第{%s}个异步调用的证书", i));
    }

    @Transactional
    @Override
    public void testExposeProxy() {
        try{
            User user = new User();
            user.setUserName("zz");
            user.setAge(10);
            user.setDes("测试");
            user.setVersion(1);
            userMapper.insert(user);
            int i = 10 / 0;
        }catch (Exception e){
            //不做处理
        }
        IAsyncService asyncService = (IAsyncService) AopContext.currentProxy();
        asyncService.async();
        System.out.println("哈哈");
    }
}
