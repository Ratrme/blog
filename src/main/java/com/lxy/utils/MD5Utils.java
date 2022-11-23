package com.lxy.utils;


import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author lxy
 * @Description 加密处理
 * @Date 2022/11/21 16:21
 */

@Component
public class MD5Utils {

    /**
     * MD5加密
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public String getMD5Str(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuilder sb = new StringBuilder();
            for (byte b : byteDigest) {
                i = b;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(i));
            }
            //32位加密
            return sb.toString();
            //16位加密
            //return sb.toString().substring(8,24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
