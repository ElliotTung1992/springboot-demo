package com.github.dge1992.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.dge1992.mybatis.domain.User;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @Author 小眼睛带鱼
 * @Description MybatisPlus自动填充
 * @Date 2019/8/13
 **/
@Log4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //log.info("开启新增填充!");
        Object originalObject = metaObject.getOriginalObject();
        //判断是否是操作用户
        if(originalObject instanceof User){
            User user = (User) originalObject;
            String des = "Jerry" + user.getDes();
            this.setInsertFieldValByName("des", des, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //log.info("开启更新填充!");
        this.setFieldValByName("operator", "Tom", metaObject);
    }
}
