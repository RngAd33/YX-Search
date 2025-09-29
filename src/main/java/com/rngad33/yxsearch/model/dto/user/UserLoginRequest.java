package com.rngad33.yxsearch.model.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 用户登录请求
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginRequest {

    private String userAccount, userPassword;

}