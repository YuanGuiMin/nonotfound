package com.meowu.nonotfound.portal.service.core.key.manager;

import com.meowu.commons.redis.sharded.helper.ShardedJedisHelper;
import com.meowu.commons.utils.security.response.Response;
import com.meowu.commons.utils.utils.AssertUtils;
import com.meowu.nonotfound.portal.service.commons.security.exception.FeignClientException;
import com.meowu.nonotfound.portal.service.commons.security.response.ResponseCode;
import com.meowu.nonotfound.portal.service.commons.security.stereotype.Manager;
import com.meowu.nonotfound.portal.service.core.key.consts.KeyConsts;
import com.meowu.nonotfound.portal.service.core.key.manager.client.PasswordRSAKeyClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;

@Manager
public class PasswordRSAKeyManager{

    @Autowired
    private PasswordRSAKeyClient passwordRSAKeyClient;

    public String getPasswordRSAPublicKey(ShardedJedis jedis){
        AssertUtils.notNull(jedis, "redis client must not be null");

        String key = ShardedJedisHelper.get(jedis, KeyConsts.RSA_PASSWORD_PUBLIC_KEY, String.class);

        // key is null and get from account-service
        if(StringUtils.isBlank(key)){
            try{
                if(ShardedJedisHelper.setIfNotExist(jedis, KeyConsts.RSA_REDIS_PASSWORD_LOCK, "1", KeyConsts.RSA_REDIS_PASSWORD_LOCK_EXPIRE)){
                    Response<String> response = passwordRSAKeyClient.getPasswordRSAPublicKey();

                    // get data
                    if(ResponseCode.SUCCESS != response.getCode() || !response.getSuccess()){
                        // exception
                        throw new FeignClientException("feign client error: get password rsa public key failed");
                    }

                    key = response.getData();

                    // put the key in cache
                    ShardedJedisHelper.save(jedis, KeyConsts.RSA_PASSWORD_PUBLIC_KEY, key);
                    // delete the lock
                    ShardedJedisHelper.delete(jedis, KeyConsts.RSA_REDIS_PASSWORD_LOCK);
                }
            }catch(Exception e){
                ShardedJedisHelper.delete(jedis, KeyConsts.RSA_REDIS_PASSWORD_LOCK);

                // throw exception anyway
                throw e;
            }
        }

        return key;
    }
}
