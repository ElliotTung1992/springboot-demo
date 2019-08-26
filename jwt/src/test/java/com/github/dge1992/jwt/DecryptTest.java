package com.github.dge1992.jwt;

import com.alibaba.fastjson.JSON;
import com.github.dge1992.jwt.doamin.User;
import com.github.dge1992.jwt.converter.BaseTransferEntity;
import com.github.dge1992.jwt.security.impl.Base64SecurityAction;
import com.github.dge1992.common.utils.MD5Util;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/23
 **/
public class DecryptTest {

    public static void main(String[] args) {

        //随机字符串，盐
        String salt = "hello5";

        User simpleObject = new User();
        simpleObject.setUser("dge");
        simpleObject.setAge(12);
        simpleObject.setName("fnn");
        simpleObject.setTips("lover");

        //数据转换成Json字符串
        String jsonString = JSON.toJSONString(simpleObject);
        //Base64编码
        String encode = new Base64SecurityAction().doAction(jsonString);
        //签名
        String md5 = MD5Util.encrypt(encode + salt);

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);

        System.out.println(JSON.toJSONString(baseTransferEntity));
    }
}
