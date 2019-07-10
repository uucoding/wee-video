package com.weecoding.service.enumerate;

import com.weecoding.common.enumerate.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * 用户相关返回码
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-10  17:22
 */
@Getter
@AllArgsConstructor
public enum UserResultEnum implements IResultCode {
    USERNAME_PASSWORD_RE_PASSWORD_IS_EMPTY(100, "用户名或密码为空!"),
    PASSWORD_RE_PASSWORD_DIFFERENCE(101, "两次密码不一致!"),
    DB_EXIST_USER(103, "用户已经存在!"),
    STORAGE_USER_SUCCESS(104, "存储用户成功!"),

    ;

    private Integer code;

    private String msg;
}
