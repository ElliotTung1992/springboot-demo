package com.github.dge1992.jwt.controller;

import com.github.dge1992.common.exception.CustomException;
import com.github.dge1992.common.exception.CustomExceptionEnum;
import com.github.dge1992.jwt.controller.dto.AuthResponse;
import com.github.dge1992.jwt.doamin.AuthObject;
import com.github.dge1992.jwt.security.DataSecurityAction;
import com.github.dge1992.jwt.security.impl.PBESecurityAction;
import com.github.dge1992.jwt.util.JwtTokenUtil;
import com.github.dge1992.jwt.validator.IAuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/23
 **/
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource(name = "simpleAuthValidator")
    private IAuthValidator authValidator;

    @RequestMapping("${jwt.auth-path}")
    public Object returnTokenAndAuth(@RequestBody AuthObject authObject){
        boolean validate = authValidator.validate(authObject);
        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String tokenOriginal = jwtTokenUtil.generateToken(authObject.getUsername(), randomKey);
            System.out.println(tokenOriginal);
            //对token进行加密
            final String token = new PBESecurityAction().doAction(tokenOriginal);
            return ResponseEntity.ok(new AuthResponse(token, randomKey));
        } else {
            //账号密码校验失败
            throw new CustomException(CustomExceptionEnum.AUTH_REQUEST_ERROR);
        }
    }

}
