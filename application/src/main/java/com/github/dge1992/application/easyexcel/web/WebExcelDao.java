package com.github.dge1992.application.easyexcel.web;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 董感恩
 * @date 2020-04-06 15:47
 * @desc
 */
@Repository
public class WebExcelDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebExcelDao.class);

    public void insertBatch(List<UploadData> uploadDataList){
        uploadDataList.stream().forEach(e -> {
            LOGGER.info("处理数据{}", JSON.toJSONString(e));
        });
    }

    public void insert(UploadData uploadData){
        LOGGER.info("处理数据{}", JSON.toJSONString(uploadData));
    }
}
