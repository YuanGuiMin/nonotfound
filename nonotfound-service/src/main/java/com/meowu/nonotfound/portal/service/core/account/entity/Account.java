package com.meowu.nonotfound.portal.service.core.account.entity;

import com.meowu.commons.utils.domain.Creatable;
import com.meowu.commons.utils.domain.Deletable;
import com.meowu.commons.utils.domain.Updatable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@Document("account")
public class Account implements Creatable, Updatable, Deletable{

    @Id
    @Field("id")
    private String id;

    @Field("user_id")
    private String userId;

    @Field("username")
    private String username;

    @Field("nickname")
    private String nickname;

    @Field("head_img")
    private String headImg;

    @Field("background_img")
    private String backgroundImg;

    @Field("description")
    private String description;

    @Field("email")
    private String email;

    @Field("phone_code")
    private String phoneCode;

    @Field("phone")
    private String phone;

    @Field("gender")
    private Gender gender;

    @Field("state")
    private AccountState state;

    @Field("deleted")
    private Boolean deleted;

    @Field("create_time")
    private Date createTime;

    @Field("update_time")
    private Date updateTime;

    @Field("delete_time")
    private Date deleteTime;
}
