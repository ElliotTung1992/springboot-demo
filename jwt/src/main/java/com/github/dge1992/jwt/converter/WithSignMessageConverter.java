package com.github.dge1992.jwt.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.github.dge1992.jwt.config.properties.JwtProperties;
import com.github.dge1992.jwt.security.DataSecurityAction;
import com.github.dge1992.common.utils.HttpKit;
import com.github.dge1992.jwt.security.impl.PBESecurityAction;
import com.github.dge1992.jwt.util.JwtTokenUtil;
import com.github.dge1992.common.utils.MD5Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 带签名的http信息转化器
 *
 * @author fengshuonan
 * @date 2017-08-25 15:42
 */
@Log4j2
public class WithSignMessageConverter extends FastJsonHttpMessageConverter {

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    DataSecurityAction dataSecurityAction;

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        //不校验认证服务
        if (HttpKit.getRequest().getServletPath().equals("/" + jwtProperties.getAuthPath())) {
            return super.read(type, contextClass, inputMessage);
        }

        InputStream in = inputMessage.getBody();
        Object o = JSON.parseObject(in, super.getFastJsonConfig().getCharset(), BaseTransferEntity.class, super.getFastJsonConfig().getFeatures());

        //先转化成原始的对象
        BaseTransferEntity baseTransferEntity = (BaseTransferEntity) o;

        //校验签名
        String token = HttpKit.getRequest().getHeader(jwtProperties.getHeader()).substring(7);
        //对token进行解密
        token = new PBESecurityAction().unlock(token);
        String md5KeyFromToken = jwtTokenUtil.getMd5KeyFromToken(token);

        String object = baseTransferEntity.getObject();
        String json = dataSecurityAction.unlock(object);
        String encrypt = MD5Util.encrypt(object + md5KeyFromToken);

        if (encrypt.equals(baseTransferEntity.getSign())) {
            log.info("签名校验成功!");
        } else {
            log.info("签名校验失败,数据被改动过!");
            //throw new GunsException(BizExceptionEnum.SIGN_ERROR);
            throw new RuntimeException("签名校验失败");
        }
        //校验签名后再转化成应该的对象
        return JSON.parseObject(json, type);
    }
}
