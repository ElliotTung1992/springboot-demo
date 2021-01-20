package com.github.dge1992.commonforward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dge
 * @date 2021-01-13 15:03
 * 公共转发服务
 * <p>
 * 1. 入口
 * 1.1 消息队列
 * 1.2 Http接口
 * 1.3 Dubbo接口
 * 2. 统一远程接口
 * 2.1 数据校验
 * 2.2 幂等处理
 * 2.3 日志记录
 * 2.4 模版定义
 * 2.5 多接口业务实现
 * 3. 对外暴露
 * 3.1 数据列表
 * 3.2 失败数据列表
 * 3.3 请求重试
 * 4. 自动重试
 * 4.1 部分Http异常自动重试
 * 4.2 部分业务异常自动重试
 */
@SpringBootApplication
public class CommonForwardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonForwardApplication.class, args);
    }

}
