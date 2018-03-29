package com.jingluu.admin.auth.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.stereotype.Component;

/**
 * 凭证匹配器
 * （用于校验密码）
 */
@Component("credentialsMatcher")
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {
    @Override
    public String getHashAlgorithmName() {
        return ShiroFilterUtils.ENCRYPTION_ALGORITHM_HASH_MD5;
    }

    @Override
    public int getHashIterations() {
        return ShiroFilterUtils.CREDENTIAL_HASH_ITERATIONS;
    }

    @Override
    public boolean isStoredCredentialsHexEncoded() {
        return true;
    }
}
