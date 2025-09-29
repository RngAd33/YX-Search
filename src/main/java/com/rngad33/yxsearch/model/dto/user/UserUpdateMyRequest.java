package com.rngad33.yxsearch.model.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 用户更新个人信息请求
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateMyRequest {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 简介
     */
    private String userProfile;

}