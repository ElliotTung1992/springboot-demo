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
 */
@RestController
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping("/retryByUuid")
    public void retryByUuid(@RequestBody String uuid){
        recordService.retryByUuid(uuid);
    }

    @GetMapping("/queryListOrderByCreateTime")
    public List<CommonReceiveObject> queryListOrderByCreateTime(){
        return recordService.queryListOrderByCreateTime();
    }

    @GetMapping("/queryNotSuccessListOrderByCreateTime")
    public List<CommonReceiveObject> queryNotSuccessListOrderByCreateTime(){
        return recordService.queryNotSuccessListOrderByCreateTime();
    }

}
