package com.spring.cloud.example.user_2.service;

import com.spring.cloud.example.user_2.dataobject.UserInfo;

public interface UserService {
    /**
     * 通过openID查询用户信息
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}
