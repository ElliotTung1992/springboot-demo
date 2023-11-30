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

    public void testEncrypt(){
        System.out.println(encryptor.encrypt(agent));
    }

    public void testDecrypt(){
        String str = "oq+J+VvRwhRY3mDfH1wo0WbYog6Cd/sm+EnMRwdlrDFGS/7gUlkh0KeYXvQcmoXW";
        System.out.println(encryptor.decrypt(str));
    }
}
