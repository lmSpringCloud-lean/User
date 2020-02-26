package com.spring.cloud.example.user_2.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * http请求返回的最外层对象
 * @param <T>
 */
@Data
public class ResultVO<T> {
    @NotBlank(message = "错误码不可为空")
    private Integer code;
    private String msg;
    //  具体内容
    private T data;
}
