package com.elliot.github.web.domain;

import lombok.Data;

@Data
public class UserInfo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
