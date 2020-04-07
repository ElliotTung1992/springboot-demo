package com.github.dge1992.application.easyexcel.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.github.dge1992.application.easyexcel.dao.ExcelDao;
import com.github.dge1992.application.easyexcel.domain.DemoData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 董感恩
 * @date 2020-04-06 15:41
 * @desc
 */
public class DemoDataListener extends AnalysisEventListener<DemoData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);

    private static final int BATCH_COUNT = 5;
    List<DemoData> list = new ArrayList<DemoData>();

    private ExcelDao excelDao;

    public DemoDataListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        this.excelDao = new ExcelDao();
    }

    public DemoDataListener(ExcelDao excelDao) {
        this.excelDao = excelDao;
    }

    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(demoData));
        list.add(demoData);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveDate();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveDate();
        LOGGER.info("所有数据解析完成！");
    }

    private void saveDate() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        excelDao.insertBatch(list);
        LOGGER.info("存储数据库成功！");
    }
}
