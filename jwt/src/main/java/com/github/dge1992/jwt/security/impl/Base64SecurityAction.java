package com.github.dge1992.jwt.security.impl;

import com.github.dge1992.jwt.security.DataSecurityAction;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * 对数据进行base64编码的方式
 *
 * @author fengshuonan
 * @date 2017-09-18 20:43
 */
public class Base64SecurityAction implements DataSecurityAction {

    @Override
    public String doAction(String beProtected) {
        return Base64Utils.encodeToString(beProtected.getBytes());
    }

    @Override
    public String unlock(String securityCode) {
        byte[] bytes = Base64Utils.decodeFromString(securityCode);
        return new String(bytes);
    }

    public static void main(String[] args) {
        int i = 12;
        i = i ^ 123;
        System.out.println(i);
        i = i ^ 123;
        System.out.println(i);

        try {
            String src = "mu rong fei fei 636";

            // 初始化加盐
            SecureRandom random = new SecureRandom();
            byte[] salt = random.generateSeed(8);

            // 口令与密钥
            String password = "wahaha";
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key = factory.generateSecret(pbeKeySpec);

            // 加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk pbe encrypt:" + HexBin.encode(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
            result = cipher.doFinal(result);
            System.out.println("jdk pbe decrypt:" + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
