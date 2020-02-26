package com.spring.cloud.example.user_2.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class UserInfo {
    @Id
    private String id;

    private String username;

    private String password;

    //  微信openid
    private String openid;

    //  1买家2卖家
    private Integer role;

    //  创建时间
    private Date create_time;

    //  修改时间
    private Date update_time;
}
