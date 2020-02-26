package com.spring.cloud.example.user_2.service.impl;

import com.spring.cloud.example.user_2.dataobject.UserInfo;
import com.spring.cloud.example.user_2.repository.UserInfoRepository;
import com.spring.cloud.example.user_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository defaultRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return defaultRepository.findByOpenid(openid);
    }
}
