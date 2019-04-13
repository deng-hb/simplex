package com.denghb.simplex.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by denghb on 2017/7/17.
 */
public class Md5Utils {

    private static Logger log = LoggerFactory.getLogger(Md5Utils.class);

    public static String md5(String plainText) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] cipherData = md5.digest(plainText.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte cipher : cipherData) {
                String toHexStr = Integer.toHexString(cipher & 0xff);
                builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
            }
            return builder.toString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
    public static String md5(byte[] bytes) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] cipherData = md5.digest(bytes);
            StringBuilder builder = new StringBuilder();
            for (byte cipher : cipherData) {
                String toHexStr = Integer.toHexString(cipher & 0xff);
                builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
            }
            return builder.toString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    // 文件MD5
    public static String md5(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                md5.update(buffer, 0, length);
            }
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (null != in)
                    in.close();
            } catch (Exception e) {
            }
        }
        return value;
    }

    public static void main(String[] args) {


        System.out.println(md5("12345678"));
    }
}
