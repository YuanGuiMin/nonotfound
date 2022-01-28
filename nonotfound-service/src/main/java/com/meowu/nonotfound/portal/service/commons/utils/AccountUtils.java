package com.meowu.nonotfound.portal.service.commons.utils;

import com.meowu.account.portal.client.account.entity.response.AccountVO;
import com.meowu.commons.utils.utils.AssertUtils;
import com.meowu.nonotfound.portal.service.core.account.entity.Account;
import com.meowu.nonotfound.portal.service.core.account.entity.AccountState;
import com.meowu.nonotfound.portal.service.core.account.entity.Gender;

public class AccountUtils{

    public static Account toAccount(AccountVO view){
        AssertUtils.notNull(view, "account view must not be null");

        Account account = new Account();
        account.setId(view.getAccountId());
        account.setUserId(view.getUserId());
        account.setUsername(view.getUsername());
        account.setNickname(view.getNickname());
        account.setHeadImg(view.getHeadImg());
        account.setBackgroundImg(view.getBackgroundImg());
        account.setDescription(view.getDescription());
        account.setEmail(view.getEmail());
        account.setPhoneCode(view.getPhoneCode());
        account.setPhone(view.getPhone());
        account.setGender(Gender.getById(view.getGender()));
        account.setState(AccountState.getById(view.getState()));
        account.setDeleted(view.getDeleted());
        account.setCreateTime(view.getCreateTime());
        account.setUpdateTime(view.getUpdateTime());
        account.setDeleted(view.getDeleted());

        return account;
    }
}
