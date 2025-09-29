package com.rngad33.yxsearch.model.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 用户注册请求体
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterRequest {

    private String userAccount, userPassword, checkPassword;

}