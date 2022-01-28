package com.meowu.nonotfound.portal.service.core.account.dao;

import com.meowu.commons.utils.utils.AssertUtils;
import com.meowu.nonotfound.portal.service.core.account.entity.Account;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class AccountDao{

    private final static Class<Account> clazz = Account.class;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(Account account){
        AssertUtils.notNull(account, "account must not be null");
        AssertUtils.hasText(account.getId(), "account id must not be null");
        AssertUtils.hasText(account.getUsername(), "account username must not be null");
        AssertUtils.notNull(account.getState(), "account state must not be null");
        AssertUtils.notNull(account.getDeleted(), "account delete state must not be null");

        mongoTemplate.save(account);
    }

    public void update(Account account){
        AssertUtils.notNull(account, "account must not be null");
        AssertUtils.hasText(account.getId(), "account id must not be null");

        // update condition
        Query query = new Query(Criteria.where("id").is(account.getId()));

        // update fields
        Update update = new Update();
        update.set("updateTime", new Date());

        if(StringUtils.isNotBlank(account.getUsername())){
            update.set("username", account.getUsername());
        }
        if(StringUtils.isNotBlank(account.getNickname())){
            update.set("nickname", account.getNickname());
        }
        if(StringUtils.isNotBlank(account.getHeadImg())){
            update.set("headImg", account.getHeadImg());
        }
        if(StringUtils.isNotBlank(account.getBackgroundImg())){
            update.set("backgroundImg", account.getBackgroundImg());
        }
        if(StringUtils.isNotBlank(account.getDescription())){
            update.set("description", account.getDescription());
        }
        if(StringUtils.isNotBlank(account.getEmail())){
            update.set("email", account.getEmail());
        }
        if(StringUtils.isNotBlank(account.getPhoneCode())){
            update.set("phoneCode", account.getPhoneCode());
        }
        if(StringUtils.isNotBlank(account.getPhone())){
            update.set("phone", account.getPhone());
        }
        if(account.getGender() != null){
            update.set("gender", account.getGender());
        }
        if(account.getState() != null){
            update.set("state", account.getState());
        }
        if(account.getDeleted() != null){
            update.set("deleted", account.getDeleted());
        }
        if(account.getDeleteTime() != null){
            update.set("deleteTime", account.getDeleteTime());
        }

        mongoTemplate.updateFirst(query, update, clazz);
    }

    public Account getById(String id){
        AssertUtils.hasText(id, "account id must not be null");

        return mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), clazz);
    }
}
