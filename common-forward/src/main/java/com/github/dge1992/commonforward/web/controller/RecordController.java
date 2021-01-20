package com.github.dge1992.commonforward.web.controller;

import com.github.dge1992.commonforward.api.model.CommonReceiveObject;
import com.github.dge1992.commonforward.biz.common.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-19 17:45
 * 远程请求记录控制器
 */
@RestController
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * 根据uuid进行重试
     *
     * @param uuid 唯一标识
     * @author dge
     * @date 2021-01-20 13:46
     */
    @PostMapping("/retryByUuid")
    public void retryByUuid(@RequestBody String uuid) {
        recordService.retryByUuid(uuid);
    }

    /**
     * 根据创建时间倒叙查询记录列表
     *
     * @return java.util.List<com.github.dge1992.commonforward.api.model.CommonReceiveObject>
     * @author dge
     * @date 2021-01-20 13:46
     */
    @GetMapping("/queryListOrderByCreateTime")
    public List<CommonReceiveObject> queryListOrderByCreateTime() {
        return recordService.queryListOrderByCreateTime();
    }

    /**
     * 根据创建时间倒叙查询错误记录列表
     *
     * @return java.util.List<com.github.dge1992.commonforward.api.model.CommonReceiveObject>
     * @author dge
     * @date 2021-01-20 13:47
     */
    @GetMapping("/queryNotSuccessListOrderByCreateTime")
    public List<CommonReceiveObject> queryNotSuccessListOrderByCreateTime() {
        return recordService.queryNotSuccessListOrderByCreateTime();
    }

}
