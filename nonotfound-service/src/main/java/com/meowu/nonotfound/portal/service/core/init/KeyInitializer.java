package com.meowu.nonotfound.portal.service.core.init;

import com.meowu.nonotfound.portal.service.commons.security.stereotype.Initializer;
import com.meowu.nonotfound.portal.service.core.key.service.KeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Initializer
public class KeyInitializer implements ApplicationRunner{

    private final static Logger logger = LoggerFactory.getLogger(KeyInitializer.class);

    @Autowired
    private KeyService keyService;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        // initialize password rsa public key
        String passwordRSAPublicKey = keyService.getPasswordRSAPublicKey();

        logger.info("password rsa public key initialize successful: {}", passwordRSAPublicKey);
    }
}
