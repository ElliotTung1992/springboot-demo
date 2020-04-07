package com.github.dge1992.application.easyexcel.dao;

import com.alibaba.fastjson.JSON;
import com.github.dge1992.application.easyexcel.domain.DemoData;
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
public class ExcelDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelDao.class);

    public void insertBatch(List<DemoData> demoDataList){
        demoDataList.stream().forEach(e -> {
            LOGGER.info("处理数据{}", JSON.toJSONString(e));
        });
    }

    public void insert(DemoData demoData){
        LOGGER.info("处理数据{}", JSON.toJSONString(demoData));
    }
}
