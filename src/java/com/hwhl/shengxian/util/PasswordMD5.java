package com.hwhl.shengxian.util;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lujingjing on 2017/8/21.
 */
public class PasswordMD5 {
    /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //加密后的字符串
        return DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
    }
}
