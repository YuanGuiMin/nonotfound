package com.meowu.nonotfound.portal.service.core.key.service;

import com.meowu.nonotfound.portal.service.core.key.manager.PasswordRSAKeyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class KeyServiceImpl implements KeyService{

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Autowired
    private PasswordRSAKeyManager passwordRSAKeyManager;

    @Override
    public String getPasswordRSAPublicKey(){
        try(ShardedJedis jedis = shardedJedisPool.getResource()){
            return passwordRSAKeyManager.getPasswordRSAPublicKey(jedis);
        }
    }
}
