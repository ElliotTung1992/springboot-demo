package com.github.dge1992.fish.encrypt;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件配置加密口令
 * 获取密文 encrypt
 * 替换明文
 */
@Component
public class JasyptComponent {

    @Value("${oa.leave.agent}")
    private String agent;

    @Autowired
    private StringEncryptor encryptor;

    public void testEncrypt(String agent){
        System.out.println("encrypt:" + encryptor.encrypt(agent));
    }

    public void testDecrypt(){
        String str = "Zgl6Mj02c1BFjN7a2F+NRl73CuxEQLQWKt40a0rHAK7GgrIF4BVsPbhIWepqYK5a";
        // String str = "FLkagpq8BuB3M4ZAFVvb4KKUiIa6utC/k2ibIjp8bbX/Ji63f1aHeiMueLR92b3J";
        System.out.println(encryptor.decrypt(str));
    }
}
