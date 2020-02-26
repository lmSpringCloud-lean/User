package com.spring.cloud.example.user_2.controller;

import com.spring.cloud.example.user_2.constant.Constants;
import com.spring.cloud.example.user_2.dataobject.UserInfo;
import com.spring.cloud.example.user_2.service.UserService;
import com.spring.cloud.example.user_2.util.CookieUtil;
import com.spring.cloud.example.user_2.util.ResultVOUtil;
import com.spring.cloud.example.user_2.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by 廖师兄
 * 2018-03-04 23:12
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response) {
        //1. openid和数据库里的数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(Constants.Result.LOGIN_FAIL);
        }

        //2. 判断角色
        if (Constants.RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(Constants.Result.ROLE_ERROR);
        }

        //3. cookie里设置openid=abc
        CookieUtil.set(response, Constants.CookieConstant.OPENID, openid, Constants.CookieConstant.expire);

        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        //判断是否已登录
        Cookie cookie = CookieUtil.get(request, Constants.CookieConstant.TOKEN);
        if (cookie != null &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(CookieUtil.get(request, Constants.CookieConstant.TOKEN)))) {
            return ResultVOUtil.success();
        }

        //1. openid和数据库里的数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(Constants.Result.LOGIN_FAIL);
        }

//        //2. 判断角色
        if (Constants.RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(Constants.Result.ROLE_ERROR);
        }

//        //3. redis设置key=UUID, value=xyz
        String token = UUID.randomUUID().toString();
        final Integer expire = Constants.CookieConstant.expire;
        String redisKey = String.format(Constants.RedisConstant.TOKEN_TEMPLATE, token);
        stringRedisTemplate.opsForValue().set(redisKey,
                openid,
                expire,
                TimeUnit.SECONDS);

//        //4. cookie里设置token=UUID
        CookieUtil.set(response, Constants.CookieConstant.TOKEN, token, expire);

        return ResultVOUtil.success();
    }
}