package com.meowu.nonotfound.portal.service.core.key.manager.client;

import com.meowu.commons.utils.security.response.Response;
import com.meowu.nonotfound.portal.service.commons.security.stereotype.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "account-service")
@Client
public interface PasswordRSAKeyClient{

    @GetMapping(value = "/api/1.0/key/password/rsa/public", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<String> getPasswordRSAPublicKey();
}
