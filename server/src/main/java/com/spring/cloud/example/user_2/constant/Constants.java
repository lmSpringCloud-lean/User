package com.spring.cloud.example.user_2.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Constants {
    /**
     * 错误码
     */
    @Getter
    @AllArgsConstructor
    enum Result {
        SUCCESS(0,"成功"),
        LOGIN_FAIL(1,"登陆失败"),
        ROLE_ERROR(2,"角色权限有误"),
        ;
        private final Integer code;
        private final String msg;
    }

    /**
     * 用户角色
     */
    @Getter
    @AllArgsConstructor
    enum RoleEnum {
        BUYER(1,"买家"),
        SELLER(2,"卖家"),
        ;
        private final Integer code;
        private final String msg;
    }

    interface CookieConstant{
        String TOKEN = "token";
        String OPENID = "openid";
        /**
         * 过期时间（单位：秒）
         */
        Integer expire = 7200;
    }

    interface RedisConstant {

        String TOKEN_TEMPLATE = "token_%s";
    }
}
