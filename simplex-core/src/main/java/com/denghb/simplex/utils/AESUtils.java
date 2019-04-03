package com.denghb.simplex.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;

/**
 *
 */
public class AESUtils {


    private static final String AES = "AES";

    private static final String CRYPT_KEY = "y2W89L6BkRAFljhN";

    private static final String IV_STRING = "dMitHORyqbeYVE0o";

    /**
     * 加密
     *
     * @param content 加密内容
     * @return 密文
     */
    public static byte[] encrypt(String content) {

        try {
            byte[] byteContent = content.getBytes("UTF-8");
            // 注意，为了能与 iOS 统一
            // 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
            byte[] enCodeFormat = CRYPT_KEY.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, AES);
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            // 指定加密的算法、工作模式和填充方式
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(byteContent);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;

    }

    public static String encrypt2Base64(String content) {
        // 同样对加密后数据进行 base64 编码

        byte[] encryptedBytes = encrypt(content);
        return new BASE64Encoder().encode(encryptedBytes);
    }

    /**
     * 解密
     *
     * @param content 密文
     * @return 明文
     */
    public static String decrypt2Base64(String content) {
        try {
            byte[] encryptedBytes = new BASE64Decoder().decodeBuffer(content);

            return decrypt(encryptedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(byte[] encryptedBytes) {
        // base64 解码
        try {
            byte[] enCodeFormat = CRYPT_KEY.getBytes();
            SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, AES);
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] result = cipher.doFinal(encryptedBytes);

            return new String(result, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String resource = "我😯1！23@4.;abc';";
        String en = encrypt2Base64(resource);


        System.out.println("en:" + en);

        String de = decrypt2Base64(en);
        System.out.println("de:" + de);


    }
}