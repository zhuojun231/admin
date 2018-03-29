package com.jingluu.admin.util;

import com.jingluu.admin.auth.shiro.ShiroFilterUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class EncryptUtils {
    public static String encrypt(String msg){
        return new SimpleHash(ShiroFilterUtils.ENCRYPTION_ALGORITHM_HASH_MD5, msg, ByteSource.Util.bytes(ShiroFilterUtils.CREDENTIAL_SALT), ShiroFilterUtils.CREDENTIAL_HASH_ITERATIONS).toHex();
    }

    public static void main(String[] args){
        System.out.println(encrypt("admin"));
    }
}
