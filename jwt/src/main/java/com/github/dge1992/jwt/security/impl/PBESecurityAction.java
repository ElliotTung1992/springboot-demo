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
import java.util.Base64;

/**
 * PBE对称加密算法
 *
 * @author fengshuonan
 * @date 2017-09-18 20:43
 */
public class PBESecurityAction implements DataSecurityAction {

    // 迭代测试
    private static final int ITERATION_COUNT = 100;
    // 使用的算法
    private static final String ALGORITHM = "PBEWithMD5AndDes";
    private static final String SALT = "[B@4f3f5";
    private static final String PASSWORD = "dge1992";

    @Override
    public String doAction(String beProtected) {
        byte[] encryptData = null;
        try {
            encryptData = encrypt(PASSWORD.toCharArray(), SALT.getBytes(), beProtected.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HexBin.encode(encryptData);
    }

    @Override
    public String unlock(String securityCode) {
        byte[] decrypt = null;
        try {
            decrypt = decrypt(PASSWORD.toCharArray(), SALT.getBytes(), HexBin.decode(securityCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(decrypt);
    }

    /**
     * @author 董感恩
     * @date 2020-02-02 20:00:01
     * @desc 产生salt,可以产生一次,可以每次都产生一个,使用特殊方式传递给对方
     **/
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        return random.generateSeed(8);
    }

    /**
     * @author 董感恩
     * @date 2020-02-02 19:59:44
     * @desc 转换为key
     **/
    public static Key getKey(char[] pwd) throws Exception {
        PBEKeySpec keySpec = new PBEKeySpec(pwd);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(keySpec);
    }

    /**
     * @author 董感恩
     * @date 2020-02-02 19:59:25
     * @desc 加密数据
     **/
    public static byte[] encrypt(char[] pwd, byte[] salt, byte[] data) throws Exception {
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        Key key = getKey(pwd);
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        return cipher.doFinal(data);
    }

    /**
     * @author 董感恩
     * @date 2020-02-02 19:59:13
     * @desc 解密数据
     **/
    public static byte[] decrypt(char[] pwd, byte[] salt, byte[] data) throws Exception {
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        Key key = getKey(pwd);
        cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        return cipher.doFinal(data);
    }

}
