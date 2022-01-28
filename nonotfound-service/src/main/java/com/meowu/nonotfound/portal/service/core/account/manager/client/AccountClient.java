package com.meowu.nonotfound.portal.service.core.account.manager.client;

import com.meowu.account.portal.client.account.entity.request.AccountRequest;
import com.meowu.account.portal.client.account.entity.response.AccountVO;
import com.meowu.commons.utils.security.response.Response;
import com.meowu.nonotfound.portal.service.commons.security.stereotype.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "account-service")
@Client
public interface AccountClient{

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Response register(@RequestBody AccountRequest request);

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Response<AccountVO> login(@RequestBody AccountRequest request);
}
