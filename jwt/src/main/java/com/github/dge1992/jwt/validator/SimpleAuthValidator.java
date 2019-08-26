package com.github.dge1992.jwt.validator;

import com.github.dge1992.jwt.doamin.AuthObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/23
 **/
@Service
public class SimpleAuthValidator implements IAuthValidator{

    @Override
    public boolean validate(AuthObject authObject) {
        String username = authObject.getUsername();
        String password = authObject.getPassword();
        if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password) && username.equals("admin") && password.equals("admin")){
            return true;
        }else{
            return false;
        }
    }
}
