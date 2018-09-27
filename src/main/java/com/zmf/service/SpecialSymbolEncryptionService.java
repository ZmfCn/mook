package com.zmf.service;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * DES对称加密算法
 * =================================================================================================================
 * 对称加密算法就是能将数据加解密。加密的时候使用密钥对数据进行加密，解密的时候使用同样的密钥对数据进行解密
 * DES是美国国家标准研究所提出的算法。由于加解密的数据安全性和密钥长度成正比，故DES的56位密钥已经形成安全隐患
 * 后来针对DES算法进行了改进，有了三重DES算法（也称DESede或Triple-DES）。全名是TDEA：Triple Data Encryption Algorithm
 * DESede针对DES算法的密钥长度较短以及迭代次数偏少问题做了相应改进，提高了安全强度
 * 不过DESede算法处理速度较慢，密钥计算时间较长，加密效率不高等问题使得对称加密算法的发展不容乐观
 * =================================================================================================================
 * Java和BouncyCastle针对DES算法的数据加密支持是不同的，主要体现在密钥长度、工作模式以及填充方式上
 * Java6只支持56位密钥，而BouncyCastle支持64位密钥，它的官网是http://www.bouncycastle.org/
 * 即便是在DESede算法上，BouncyCastle的密钥长度也要比Java的密钥长度长
 * =================================================================================================================
 * 另外，Java的API中仅仅提供了DES、DESede、PBE三种对称加密算法密钥材料实现类
 * =================================================================================================================
 * 关于Java加解密的更多算法实现，可以参考此爷的博客http://blog.csdn.net/kongqz/article/category/800296
 * =================================================================================================================
 */
@Component
public class SpecialSymbolEncryptionService {
    //算法名称
    private static final String KEY_ALGORITHM = "DES";
    //算法名称/加密模式/填充方式
    //DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

    //key
    private static String key = "BLzCL85uepg=";
    /*static {
        try {
            key = initkey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }*/

    @Test
    public void test() {
        try {
            String mykey = initkey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成密钥
     */
    private static String initkey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM); //实例化密钥生成器
        kg.init(56);                                               //初始化密钥生成器
        SecretKey secretKey = kg.generateKey();                    //生成密钥
        return Base64.encodeBase64String(secretKey.getEncoded());  //获取二进制密钥编码形式
    }

    /**
     * 转换密钥
     */
    private static Key toKey(byte[] key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key);                                      //实例化Des密钥
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM); //实例化密钥工厂
        return keyFactory.generateSecret(dks);                      //生成密钥
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return 加密后的数据
     */
    private static String encrypt(String data, String key) throws Exception {
        Key k = toKey(Base64.decodeBase64(key));                           //还原密钥
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);              //实例化Cipher对象，它用于完成实际的加密操作
        cipher.init(Cipher.ENCRYPT_MODE, k);                               //初始化Cipher对象，设置为加密模式
        return Base64.encodeBase64String(cipher.doFinal(data.getBytes())); //执行加密操作。加密后的结果通常都会用Base64编码进行传输
    }

    /**
     * 解密数据
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return 解密后的数据
     */
    private static String decrypt(String data, String key) throws Exception {
        Key k = toKey(Base64.decodeBase64(key));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);                           //初始化Cipher对象，设置为解密模式
        return new String(cipher.doFinal(Base64.decodeBase64(data)));  //执行解密操作
    }


    /**
     * to encrypt a data string
     *
     * @param data the data to be encrypted
     * @return the data encrypted
     */
    public String encrypt(String data) {
        try {
            return encrypt(data, key);
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
            return decrypt(data, key);
        } catch (Exception e) {
            return data;
        }
    }
}