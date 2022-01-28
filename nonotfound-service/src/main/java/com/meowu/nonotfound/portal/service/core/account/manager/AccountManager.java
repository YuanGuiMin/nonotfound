package com.meowu.nonotfound.portal.service.core.account.manager;

import com.meowu.account.portal.client.account.entity.request.AccountRequest;
import com.meowu.commons.utils.security.response.Response;
import com.meowu.commons.utils.utils.AssertUtils;
import com.meowu.nonotfound.portal.service.commons.security.response.ResponseCode;
import com.meowu.nonotfound.portal.service.commons.security.stereotype.Manager;
import com.meowu.nonotfound.portal.service.core.account.manager.client.AccountClient;
import org.springframework.beans.factory.annotation.Autowired;

@Manager
public class AccountManager{

    @Autowired
    private AccountClient accountClient;

    public void register(String username, String password){
        AssertUtils.hasText(username, "account register username must not be null");
        AssertUtils.hasText(password, "account register password must not be null");

        AccountRequest request = new AccountRequest();
        request.setUsername(username);
        request.setPassword(password);

        Response response = accountClient.register(request);

        if(ResponseCode.SUCCESS != response.getCode() && !response.getSuccess()){

        }
    }
}
