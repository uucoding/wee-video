package com.weecoding.common.util;

import com.weecoding.common.enumerate.SecurityCodeEnum;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密解密相关工具类类
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-13  10:53
 */
public class SecretUtil {

    /**
     * 创建MD5加密盐
     * @return
     */
    public static String createMD5Salt(int length) {
        return DigestUtils.md2Hex(S.newRandom(length, SecurityCodeEnum.MIX_LOWER_LETTER_AND_NUMBER));
    }

    /**
     * MD5加密
     * @param password 密码
     * @return 加密后的密码
     */
    public static String encryptMD5(String password) {
            return new Md5Hash(password).toString();
    }

    /**
     * MD5加密
     * @param password 密码
     * @param salt 盐
     * @return 加密后的密码
     */
    public static String encryptMD5WithSalt(String password, String salt) {
        return new Md5Hash(password, salt, 1024).toString();
    }
}
