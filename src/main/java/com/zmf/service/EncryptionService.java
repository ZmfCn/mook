package com.zmf.service;

import org.junit.Test;
import org.springframework.stereotype.Component;

import java.security.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES加密算法
 * 这个加密算法是对称的加密算法
 * 这个加密算法，在本系统中的应用，主要是因为该加密算法加密后
 * 密文都是字符串和数字的，没有其他字符。这样的密文可以应用在url地址上，
 * 不用担心被URL 地址上传递的数字被转义
 *
 * @author Administrator
 */
@Component
public class EncryptionService {

    /**
     * 加密算法,可用 DES,DESede,Blowfish.
     */
    private final static String ALGORITHM = "DES";
    private final static String KEY = "4ui56rtg4df4q1g5sf4dz15";

    @Test
    public void test() {
        String data = "488888888888888111111111111111111111111118865";
        try {
            EncryptionService r = new EncryptionService();
            String t1 = r.encrypt(data);
            String t2 = r.decrypt(t1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * to encrypt a data string
     *
     * @param data the data to be encrypted
     * @return the data encrypted
     */
    public String encrypt(String data) {
        try {
            return encrypt(data, KEY);
        } catch (Exception e) {
            return data;
        }
    }

    /**
     * to decrypt a data string that had been encrypted
     *
     * @param data the data encrypted
     * @return the origin data
     */
    public String decrypt(String data) {
        try {
            return decrypt(data, KEY);
        } catch (Exception e) {
            return data;
        }
    }


    /**
     * DES解密算法
     *
     * @param data     the encrypted data
     * @param cryptKey the key
     * @return origin data
     * @throws Exception except
     */
    private static String decrypt(String data, String cryptKey) throws Exception {
        return new String(decrypt(hex2byte(data.getBytes()),
                cryptKey.getBytes()));
    }

    /**
     * DES加密算法
     *
     * @param data     the data
     * @param cryptKey the key
     * @return the encrypted data
     * @throws Exception except
     */
    private static String encrypt(String data, String cryptKey)
            throws Exception {
        return byte2hex(encrypt(data.getBytes(), cryptKey.getBytes()));
    }

    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 现在，获取数据并解密
        // 正式执行解密操作
        return cipher.doFinal(data);
    }

    private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs.append("0").append(stmp);
            else
                hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }
}