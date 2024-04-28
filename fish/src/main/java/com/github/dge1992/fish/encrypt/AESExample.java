package com.github.dge1992.fish.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESExample {

    public static byte[] encrypt(String plaintext, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plaintext.getBytes());
    }

    public static String decrypt(byte[] ciphertext, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            String plaintext = "Hello, World!";
            String key = "ThisIsASecretKey";

            // 加密
            byte[] encrypted = encrypt(plaintext, key);
            String encryptedText = Base64.getEncoder().encodeToString(encrypted);
            System.out.println("加密后: " + encryptedText);

            // 解密
            byte[] decrypted = Base64.getDecoder().decode(encryptedText);
            String decryptedText = decrypt(decrypted, key);
            System.out.println("解密后: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
